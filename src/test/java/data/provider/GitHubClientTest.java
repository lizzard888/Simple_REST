package data.provider;

import kong.unirest.GetRequest;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static data.provider.GitHubClient.validateRequest;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GitHubClientTest {

    private static GitHubClient client;

    @BeforeClass
    public static void beforeClass() throws Exception {
        MockitoAnnotations.openMocks(GitHubClientTest.class);
        client = new GitHubClient();
    }

    @Test
    public void getDataWillProvideValidDataObject() throws Exception{
        // Predefined JSON data will be provided.
        client.url = "https://run.mocky.io/v3/5d5e8c02-bd10-4816-9edc-dd353915e733?{login}";
        assertThat(client.getData("shrek")).hasFieldOrPropertyWithValue("id", "434565")
                .hasFieldOrPropertyWithValue("login", "shrek")
                .hasFieldOrPropertyWithValue("name", "Ramu Ramamurthy")
                .hasFieldOrPropertyWithValue("type", "User")
                .hasFieldOrPropertyWithValue("avatarUrl","https://avatars.githubusercontent.com/u/434565?v=4")
                .hasFieldOrPropertyWithValue("createdAt","2010-10-10T19:34:31Z")
                .hasFieldOrPropertyWithValue("followers", "1")
                .hasFieldOrPropertyWithValue("public_repos", "14")
                .hasOnlyFields("id", "login", "name", "type", "avatarUrl", "createdAt", "followers", "public_repos");;
    }

    @Test
    public void okRequestPassesValidationOtherwiseNo() throws Exception{
        HttpRequest<GetRequest> request = Mockito.mock(GetRequest.class);
        HttpResponse<JsonNode> requestAsJson = Mockito.mock(HttpResponse.class);
        when(request.asJson()).thenReturn(requestAsJson);

        when(requestAsJson.getStatus()).thenReturn(200);
        assertThatNoException().isThrownBy(() -> validateRequest(request));
        when(requestAsJson.getStatus()).thenReturn(404);
        assertThatExceptionOfType(NotFoundDataForGivenParameterException.class).isThrownBy(() -> validateRequest(request));
    }

}