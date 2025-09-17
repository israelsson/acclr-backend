package se.accelor.accelor.adapters.api.dto;

import lombok.Builder;
import lombok.Data;
import se.accelor.accelor.adapters.spi.dto.PullRequestRequestDto;

@Data
@Builder
public class CreatePullRequestRequestDto {

    String owner;

    String repo;

    String title;

    String body;

    public static final PullRequestRequestDto toCoreModel(CreatePullRequestRequestDto dto) {

        return PullRequestRequestDto.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .base("main")
                .head(dto.getRepo())
                .owner(dto.getOwner())
                .repo(dto.getRepo())
                .build();

    }

}
