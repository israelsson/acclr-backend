package se.accelor.accelor.adapters.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePullRequestResponseDto {

    String title;

    String body;

}
