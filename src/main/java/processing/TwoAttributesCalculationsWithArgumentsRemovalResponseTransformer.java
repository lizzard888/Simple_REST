package processing;

import com.google.gson.JsonObject;

/**
 * This class makes an additional step with removing arguments to the calculation attribute from JSON response.
 */
 abstract public class TwoAttributesCalculationsWithArgumentsRemovalResponseTransformer extends TwoAttributesCalculationsResponseTransformer {
    public TwoAttributesCalculationsWithArgumentsRemovalResponseTransformer(String firstArgumentName, String secondArgumentName) {
        super(firstArgumentName, secondArgumentName);
    }

    @Override
    protected void processJson(JsonObject response) {
        super.processJson(response);
        response.remove(firstArgumentName);
        response.remove(secondArgumentName);
    }
}
