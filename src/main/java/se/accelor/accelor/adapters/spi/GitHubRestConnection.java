package se.accelor.accelor.adapters.spi;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import se.accelor.accelor.adapters.spi.dto.CreateBranchRequestDto;
import se.accelor.accelor.adapters.spi.dto.GitHubCreatePrRequestDto;
import se.accelor.accelor.adapters.spi.dto.PullRequestRequestDto;
import se.accelor.accelor.adapters.spi.dto.UpdateContentRequest;
import se.accelor.accelor.config.ApplicationConstants;
import se.accelor.accelor.core.model.*;
import se.accelor.accelor.core.ports.GitHubConnection;


@Slf4j
@AllArgsConstructor
public class GitHubRestConnection implements GitHubConnection {

    private RestTemplate restTemplate;

    @Override
    public Branch getBranchByName(String owner, String repo, String branchName) {

        return restTemplate.getForObject(
                ApplicationConstants.GITHUB_API_OWNER_AND_REPO_PATH.formatted(owner, repo) + "/git/" + branchName,
                Branch.class
        );
    }


    @Override
    public String createNewBranch(String owner, String repo, Branch mainBranch) {

        final var refName = "refs/heads/patch-" + System.currentTimeMillis();

        final var request = CreateBranchRequestDto.builder()
                .ref(refName)
                .sha(mainBranch.getObject().getSha())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer " + ApplicationConstants.GITHUB_PAT_V2);

        HttpEntity<?> httpEntity = new HttpEntity<Object>(request, headers);

        final var githubResponse = restTemplate.postForEntity(
                ApplicationConstants.GITHUB_API_GIT_REFS_PATH.formatted(owner, repo),
                httpEntity,
                Branch.class
        );

        return refName;
    }

    @Override
    public Blob getContentAsBlob(
            String owner,
            String repo,
            Branch branch,
            String file
    ) {

        return restTemplate.getForObject(
                ApplicationConstants.GITHUB_API_CONTENT_PATH.formatted(owner, repo) + "/" + file + "?ref=" + branch.getRef().replace("refs/heads/", ""),
                Blob.class
        );
    }

    @Override
    public PullRequest createPullRequest(PullRequestRequestDto request, Branch branch) {

        final var requestDto = GitHubCreatePrRequestDto.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .head(branch.getRef())
                .base(request.getBase())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer " + ApplicationConstants.GITHUB_PAT_V2);

        HttpEntity<?> httpEntity = new HttpEntity<Object>(requestDto, headers);

        final var githubResponse = restTemplate.postForEntity(
                ApplicationConstants.GITHUB_API_PULL_PATH.formatted(request.getOwner(), request.getRepo()),
                httpEntity,
                PullRequest.class
        );

        return githubResponse.getBody();
    }

    @Override
    public ContentWrapper updateFileWithNewContent(
            String owner,
            String repo,
            Branch branch,
            String newContent,
            Blob blob,
            String file
    ) {

        final var request = UpdateContentRequest.builder()
                .content(newContent)
                .message("New setup for: " + branch.getRef())
                .committer(Committer.builder()
                        .email("anders.i84@gmail.com")
                        .name("israelsson")
                        .build()
                )
                .sha(blob.getSha())
                .branch(branch.getRef().replace("refs/heads/", ""))
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer " + ApplicationConstants.GITHUB_PAT_V2);
        headers.set("X-GitHub-Api-Version", "2022-11-28");

        HttpEntity<?> httpEntity = new HttpEntity<Object>(request, headers);

        String url = String.format(
                ApplicationConstants.GITHUB_API_CONTENT_PATH + "/%s?ref=%s",
                owner,
                repo,
                file,
                branch.getRef().replace("refs/heads/", "")
        );

        final var response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                httpEntity,
                ContentWrapper.class
        );


        return response.getBody();
    }
}
