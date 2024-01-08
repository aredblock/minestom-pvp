package net.bytemc.pvp.tools;

import com.google.gson.*;
import net.minestom.server.item.Material;

import java.lang.reflect.Type;

public class MinestomToolsSerializer implements JsonSerializer<MinestomTools>, JsonDeserializer<MinestomTools> {

    @Override
    public MinestomTools deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var element = jsonElement.getAsJsonObject();
        var material = Material.fromNamespaceId(element.get("itemNamespaceId").getAsString());
        var attackDamageValue = element.get("attackDamageValue").getAsDouble();
        var attackSpeedValue = element.get("attackSpeedValue").getAsFloat();
        return new MinestomTools(material, attackDamageValue, attackSpeedValue);
    }

    @Override
    public JsonElement serialize(MinestomTools minestomTools, Type type, JsonSerializationContext jsonSerializationContext) {
        var object = new JsonObject();
        object.addProperty("itemNamespaceId", minestomTools.getItemNamespaceId().name());
        object.addProperty("attackDamageValue", minestomTools.getAttackDamageValue());
        object.addProperty("attackSpeedValue", minestomTools.getAttackSpeedValue());
        return object;
    }
}
