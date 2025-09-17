package se.accelor.accelor.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Verification {

    boolean verified;
    String reason;
    String signature;
    String payload;
    String verifiedAt;
}
