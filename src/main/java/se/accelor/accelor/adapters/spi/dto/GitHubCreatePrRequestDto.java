package se.accelor.accelor.adapters.spi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GitHubCreatePrRequestDto {

    String title;
    String body;
    String head;
    String base;

}
