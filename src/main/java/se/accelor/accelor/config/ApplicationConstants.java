package se.accelor.accelor.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {

    // Controllers
    public static final String API_BASE_PATH = "/api/v1";
    public static final String API_PATH_CREATE_SETUP_PATH = "/create";


    // HitHub resources
    public static final String GITHUB_API_URL = "https://api.github.com";
    public static final String GITHUB_API_REPO_PATH = GITHUB_API_URL + "/repos";
    public static final String GITHUB_API_OWNER_AND_REPO_PATH = GITHUB_API_REPO_PATH + "/%s/%s";
    public static final String GITHUB_API_PULL_PATH = GITHUB_API_OWNER_AND_REPO_PATH + "/pulls";
    public static final String GITHUB_API_CONTENT_PATH = GITHUB_API_OWNER_AND_REPO_PATH + "/contents";
    public static final String GITHUB_API_GIT_REFS_PATH = GITHUB_API_OWNER_AND_REPO_PATH + "/git/refs";

}
