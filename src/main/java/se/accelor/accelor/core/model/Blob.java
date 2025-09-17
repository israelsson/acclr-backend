package se.accelor.accelor.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Blob {

    String sha;

    String content;
}
