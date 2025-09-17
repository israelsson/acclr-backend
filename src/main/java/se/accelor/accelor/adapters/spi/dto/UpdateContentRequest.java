package se.accelor.accelor.adapters.spi.dto;

import lombok.Builder;
import lombok.Data;
import se.accelor.accelor.core.model.Committer;

@Data
@Builder
public class UpdateContentRequest {

    String content;
    String message;
    Committer committer;
    String sha;
    String branch;
}
