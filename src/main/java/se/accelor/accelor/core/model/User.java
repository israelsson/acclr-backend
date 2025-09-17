package se.accelor.accelor.core.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    String login;
    long id;
    String url;
    String htmlUrl;
    String type;
    String avatarUrl;
}
