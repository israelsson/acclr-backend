package se.accelor.accelor.adapters.spi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PullRequestRequestDto {
    String title;
    String body;
    String head;
    String base;
    String owner;
    String repo;
}
