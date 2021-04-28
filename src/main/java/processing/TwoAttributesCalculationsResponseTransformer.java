package processing;

import com.google.gson.JsonObject;

/**
 * Class responsible for adding calculations attribute of two attributes int value to the JSON response.
 */
public abstract class TwoAttributesCalculationsResponseTransformer extends JsonProcessingResponseTransformer {
    protected final String firstArgumentName;
    protected final String secondArgumentName;

    public TwoAttributesCalculationsResponseTransformer(String firstArgumentName, String secondArgumentName) {
        super();
        this.firstArgumentName = firstArgumentName;
        this.secondArgumentName = secondArgumentName;
    }

    protected void processJson(JsonObject response) {
        response.addProperty("calculations",
                calculate(
                        intFromAttribute(response.getAsJsonObject(), firstArgumentName),
                        intFromAttribute(response.getAsJsonObject(), secondArgumentName)
                )
        );
    }

    protected int intFromAttribute(JsonObject response, String attributeName){
        int attributeAsInt;
        try{
            attributeAsInt = response.get(attributeName).getAsInt();
        } catch(Exception e) {
            attributeAsInt = 0;
        }
        return attributeAsInt;
    }

    protected abstract String calculate(int firstArgument, int secondArgument);
}
