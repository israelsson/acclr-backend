package se.accelor.accelor.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
@AllArgsConstructor
public class FileContentService {

    public String getBase64StringAsHumanReadableString(String base64string) {

        base64string = base64string.replaceAll("\\s", "");
        byte[] decodedBytes = Base64.getDecoder().decode(base64string);
        return new String(decodedBytes);
    }

    public String encodeAsBase64(String humanReadable) {

        return Base64.getEncoder().encodeToString(humanReadable.getBytes(StandardCharsets.UTF_8));
    }

    public String appendNewResource(String oldString) {

        final var addResourceString = """
                
                resource "vercel_project_domain" "example" {
                  project_id = vercel_project.example.id
                  domain     = "myproject-domain.vercel.app"
                }
                """;

        return oldString + addResourceString;
    }
}
