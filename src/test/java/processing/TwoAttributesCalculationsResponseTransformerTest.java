package processing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TwoAttributesCalculationsResponseTransformerTest {

    private static TwoAttributesCalculationsResponseTransformer twoAttributesCalculationsResponseTransformer;
    private static Gson gson;
    protected static final String jsonForTest = "{" +
            "\"first\":\"6\"," +
            "\"second\":\"2\"," +
            "\"third\":\"9821495\"}";

    @BeforeClass
    public static void beforeClass() throws Exception {
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
        twoAttributesCalculationsResponseTransformer = new TwoAttributesCalculationsResponseTransformer("first", "second") {
            @Override
            protected String calculate(int firstArgument, int secondArgument) {
                return "calculated";
            }
        };
    }

    @Test
    public void checkIfCalculationsAreAddedToJson() {
        String jsonProcessed = jsonForTest;
        String jsonExpected = "{" +
                "\"first\":\"6\"," +
                "\"second\":\"2\"," +
                "\"third\":\"9821495\"," +
                "\"calculations\":\"calculated\"}";
        JsonObject jsonObject = gson.fromJson(jsonProcessed, JsonObject.class);
        twoAttributesCalculationsResponseTransformer.processJson(jsonObject);
        jsonProcessed = gson.toJson(jsonObject);
        assertThat(jsonProcessed).isEqualTo(jsonExpected);
    }

    @Test
    public void checkIntExtraction() {
        JsonObject jsonObject = gson.fromJson(jsonForTest, JsonObject.class);
        jsonObject.add("string", gson.toJsonTree("string"));
        assertThat(twoAttributesCalculationsResponseTransformer.intFromAttribute(jsonObject, "first")).isEqualTo(6);
        assertThat(twoAttributesCalculationsResponseTransformer.intFromAttribute(jsonObject, "second")).isEqualTo(2);
        assertThat(twoAttributesCalculationsResponseTransformer.intFromAttribute(jsonObject, "third")).isEqualTo(9821495);
        assertThat(twoAttributesCalculationsResponseTransformer.intFromAttribute(jsonObject, "notExisting")).isEqualTo(0);
        assertThat(twoAttributesCalculationsResponseTransformer.intFromAttribute(jsonObject, "string")).isEqualTo(0);
    }
}