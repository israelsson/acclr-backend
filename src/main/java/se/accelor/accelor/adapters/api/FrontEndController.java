package se.accelor.accelor.adapters.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.accelor.accelor.adapters.api.dto.CreatePullRequestRequestDto;
import se.accelor.accelor.config.ApplicationConstants;
import se.accelor.accelor.core.service.FrontEndService;
import se.accelor.accelor.core.service.GitHubService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApplicationConstants.API_BASE_PATH)
public class FrontEndController {

    private GitHubService gitHubService;
    private FrontEndService frontEndService;

    @PostMapping(
            path = ApplicationConstants.API_PATH_CREATE_SETUP_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> createForOwnerAndRepo(
            @RequestBody CreatePullRequestRequestDto request
    ) {

        log.info("Incoming request to create new setup");
        final var response = gitHubService.createForOwnerAndRepo(CreatePullRequestRequestDto.toCoreModel(request));
        return ResponseEntity.ok(response.toString());
    }
}
