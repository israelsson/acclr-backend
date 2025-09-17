package se.accelor.accelor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import se.accelor.accelor.adapters.spi.GitHubRestConnection;
import se.accelor.accelor.core.ports.GitHubConnection;

@Configuration
public class CoreConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    GitHubConnection gitHubConnection(RestTemplate restTemplate) {

        return new GitHubRestConnection(restTemplate);
    }
}
