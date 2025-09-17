package se.accelor.accelor.core.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Committer {

    String name;
    String email;
}
