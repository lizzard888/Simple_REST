package processing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.ResponseTransformer;

/**
 * Class responsible for transforming response into JSON with additional processing step defined.
 */
public abstract class JsonProcessingResponseTransformer implements ResponseTransformer {
    private Gson gson;

    public JsonProcessingResponseTransformer() {
        this.gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

    @Override
    public String render(Object rawResponse) {
        JsonObject response =  gson.toJsonTree(rawResponse).getAsJsonObject();
        processJson(response);
        return gson.toJson(response);
    }

    protected abstract void processJson(JsonObject response);

}
