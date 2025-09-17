package se.accelor.accelor.core.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContentWrapper {

    Content content;

    Commit commit;


    @Data
    @Builder
    public static class Content {

        String name;
        String path;
        String sha;
        long size;
        String url;
        String htmlUrl;
        String gitUrl;
        String downloadUrl;
        String type;

    }

    @Data
    @Builder
    public static class Commit {

        String sha;
        String nodeId;
        String url;
        String htmlUrl;
        Author author;
        Committer committer;
        Tree tree;
        String message;
        List<Parent> parents;
        Verification verification;
    }
}
