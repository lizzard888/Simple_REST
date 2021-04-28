package processing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class TwoAttributesCalculationsWithArgumentsRemovalResponseTransformerTest {
    private static TwoAttributesCalculationsWithArgumentsRemovalResponseTransformer twoAttributesCalculationsResponseTransformer;
    private static Gson gson;

    @BeforeClass
    public static void beforeClass() throws Exception {
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
        twoAttributesCalculationsResponseTransformer = new TwoAttributesCalculationsWithArgumentsRemovalResponseTransformer("first", "second") {
            @Override
            protected String calculate(int firstArgument, int secondArgument) {
                return "calculated";
            }
        };
    }

    @Test
    public void checkIfArgumentsAreRemovedAfterProcessing() {
        String jsonProcessed = TwoAttributesCalculationsResponseTransformerTest.jsonForTest;
        String jsonExpected = "{" +
                "\"third\":\"9821495\"," +
                "\"calculations\":\"calculated\"}";
        JsonObject jsonObject = gson.fromJson(jsonProcessed, JsonObject.class);
        twoAttributesCalculationsResponseTransformer.processJson(jsonObject);
        jsonProcessed = gson.toJson(jsonObject);
        assertThat(jsonProcessed).isEqualTo(jsonExpected);

    }
}