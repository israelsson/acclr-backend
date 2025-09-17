package se.accelor.accelor.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parent {

    String sha;
    String url;
    String htmlUrl;
}
