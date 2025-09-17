package se.accelor.accelor.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PullRequest {

    String url;
    long id;
    String nodeId;
    String htmlUrl;
    String diffUrl;
    String patchUrl;
    String issueUrl;
    long number;
    String state;
    boolean locked;
    String title;
    User user;
    String body;
    String createdAt;
    String updatedAt;
    String closedAt;
    String mergedAt;
}
