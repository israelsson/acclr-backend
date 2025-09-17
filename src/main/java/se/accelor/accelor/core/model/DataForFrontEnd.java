package se.accelor.accelor.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataForFrontEnd {

    String[] services = {"cms", "ecommerce", "Mailerlite", "Resend"};
}
