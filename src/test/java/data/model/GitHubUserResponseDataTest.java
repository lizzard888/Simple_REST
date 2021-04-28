package data.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class GitHubUserResponseDataTest {
    @Test
    public void convertionBetweenDataTypesWorksAsExpected() throws Exception{
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        String gitHubJson = "{ " +
                "\"id\": \"2021\", " +
                "\"login\": \"lizzard888\", " +
                "\"name\": \"Shrek\", " +
                "\"type\": \"User\", " +
                "\"avatar_url\": \"https://avatars.githubusercontent.com/u/shrek\", " +
                "\"created_at\": \"2008-12-19T05:01:55Z\", " +
                "\"followers\": \"2\", " +
                "\"public_repos\": \"70\" }";

        GitHubUserResponseData gitHubUserResponseData = gson.fromJson(gitHubJson, GitHubUserResponseData.class);
        assertThat(gitHubUserResponseData.convert())
                .hasFieldOrPropertyWithValue("id", "2021")
                .hasFieldOrPropertyWithValue("login", "lizzard888")
                .hasFieldOrPropertyWithValue("name", "Shrek")
                .hasFieldOrPropertyWithValue("type", "User")
                .hasFieldOrPropertyWithValue("avatarUrl", "https://avatars.githubusercontent.com/u/shrek")
                .hasFieldOrPropertyWithValue("createdAt", "2008-12-19T05:01:55Z")
                .hasFieldOrPropertyWithValue("followers", "2")
                .hasFieldOrPropertyWithValue("public_repos", "70")
                .hasOnlyFields("id", "login", "name", "type", "avatarUrl", "createdAt", "followers", "public_repos");
    }
}