package se.accelor.accelor.adapters.spi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBranchRequestDto {

    String ref;
    String sha;
}
