package se.accelor.accelor.core.ports;

import se.accelor.accelor.core.model.Blob;
import se.accelor.accelor.core.model.Branch;
import se.accelor.accelor.core.model.ContentWrapper;
import se.accelor.accelor.adapters.spi.dto.PullRequestRequestDto;
import se.accelor.accelor.core.model.PullRequest;

public interface GitHubConnection {

    Branch getBranchByName(String owner, String repo, String branchName);

    String createNewBranch(String owner, String repo, Branch sha);

    Blob getContentAsBlob(String owner, String repo, Branch branch, String file);

    PullRequest createPullRequest(PullRequestRequestDto request, Branch branch);

    ContentWrapper updateFileWithNewContent(String owner, String repo, Branch branch, String newContent, Blob blob, String file);
}
