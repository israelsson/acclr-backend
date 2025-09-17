package se.accelor.accelor.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {

    String name;
    String email;
    String date;
}
