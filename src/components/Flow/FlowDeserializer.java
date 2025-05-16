package components.Flow;

import com.google.gson.*;

import java.lang.reflect.Type;

//2.1

public class FlowDeserializer implements JsonDeserializer<Flow> {
	
    @Override
    public Flow deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("type").getAsString();

        switch (type) {
            case "Debit":
                return context.deserialize(obj, Debit.class);
            case "Credit":
                return context.deserialize(obj, Credit.class);
            case "Transfert":
                return context.deserialize(obj, Transfert.class);
            default:
                throw new JsonParseException("Unknown flow type: " + type);
        }
    }

}
