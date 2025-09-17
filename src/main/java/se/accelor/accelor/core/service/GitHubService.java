package se.accelor.accelor.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.accelor.accelor.core.model.Blob;
import se.accelor.accelor.core.model.Branch;
import se.accelor.accelor.core.model.ContentWrapper;
import se.accelor.accelor.adapters.spi.dto.PullRequestRequestDto;
import se.accelor.accelor.core.model.PullRequest;
import se.accelor.accelor.core.ports.GitHubConnection;

@Slf4j
@Service
@AllArgsConstructor
public class GitHubService {

    private FileContentService fileContentService;

    private GitHubConnection gitHubConnection;

    public PullRequest createForOwnerAndRepo(PullRequestRequestDto request) {

        final var owner = request.getOwner();
        final var repo = request.getRepo();

        final var mainBranch = getMainBranch(owner, repo);
        final var refToCreatedBranch = createNewBranch(owner, repo, mainBranch);
        final var createdBranch = getBranchByName(owner, repo, refToCreatedBranch);
        final var mainBlob = getContentAsBlob(
                owner,
                repo,
                createdBranch,
                "main.tf"
        );

        final var fileContent = fileContentService.getBase64StringAsHumanReadableString(mainBlob.getContent());
        log.error("fileContent: {}", fileContent);
        final var updatedString = fileContentService.appendNewResource(fileContent);
        log.error("updatedString: {}", updatedString);
        final var newContentAsBase64 = fileContentService.encodeAsBase64(updatedString);
        log.error("newContentAsBase64: {}", newContentAsBase64);

        final var newContent = updateFileContentInBranch(
                owner,
                repo,
                createdBranch,
                newContentAsBase64,
                mainBlob
        );

        return gitHubConnection.createPullRequest(request, createdBranch);
    }

    public Branch getMainBranch(String owner, String repo) {

        return gitHubConnection.getBranchByName(owner, repo,"refs/heads/main");
    }

    public Branch getBranchByName(String owner, String repo, String name) {

        return gitHubConnection.getBranchByName(owner, repo, name);
    }

    public String createNewBranch(String owner, String repo, Branch mainBranch) {

        return gitHubConnection.createNewBranch(owner, repo, mainBranch);
    }

    public Blob getContentAsBlob(String owner, String repo, Branch branch, String file) {

        return gitHubConnection.getContentAsBlob(
                owner,
                repo,
                branch,
                file
        );
    }

    public ContentWrapper updateFileContentInBranch(
            String owner,
            String repo,
            Branch branch,
            String newContent,
            Blob blob
    ) {

        return gitHubConnection.updateFileWithNewContent(owner, repo, branch, newContent, blob, "main.tf");
    }
}
