package processing;

import com.google.gson.JsonObject;
import data.model.UserResponseData;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class JsonProcessingResponseTransformerTest {

    private static JsonProcessingResponseTransformer jsonProcessingResponseTransformer;

    @BeforeClass
    public static void beforeClass() throws Exception {
        jsonProcessingResponseTransformer = new JsonProcessingResponseTransformer() {
            @Override
            protected void processJson(JsonObject response) {}
        };
    }

    @Test
    public void checkIfUserResponseDataRendersToJsonProperly() throws Exception{
        UserResponseData responseData = new UserResponseData(
                "2021",
                "lizzard888",
                "Shrek",
                "User",
                "2008-12-19T05:01:55Z",
                "https://avatars.githubusercontent.com/u/shrek",
                "2",
                "70");
        String wantedJson = "{" +
                "\"id\":\"2021\"," +
                "\"login\":\"lizzard888\"," +
                "\"name\":\"Shrek\"," +
                "\"type\":\"User\"," +
                "\"avatarUrl\":\"https://avatars.githubusercontent.com/u/shrek\"," +
                "\"createdAt\":\"2008-12-19T05:01:55Z\"," +
                "\"followers\":\"2\"," +
                "\"public_repos\":\"70\"}";
        assertThat(jsonProcessingResponseTransformer.render(responseData)).isEqualTo(wantedJson);
    }

}