package se.accelor.accelor.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {

    String ref;
    String nodeId;
    String url;
    BranchDetails object;

    @Data
    @Builder
    public static class BranchDetails {

        String sha;
        String type;
        String url;

    }
}
