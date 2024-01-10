package net.bytemc.pvp.tools;

import com.google.gson.*;
import net.minestom.server.item.Material;

import java.lang.reflect.Type;

public class MinestomToolsSerializer implements JsonSerializer<MinestomTools>, JsonDeserializer<MinestomTools> {

    @Override
    public MinestomTools deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var element = jsonElement.getAsJsonObject();
        var material = Material.fromNamespaceId(element.get("item").getAsString());
        var attackDamageValue = element.get("attackDamageValue").getAsFloat();
        var legacyAttackDamageValue = element.get("legacyAttackDamageValue").getAsFloat();
        var attackSpeedValue = element.get("attackSpeedValue").getAsFloat();
        return new MinestomTools(material, attackDamageValue, attackSpeedValue, legacyAttackDamageValue);
    }

    @Override
    public JsonElement serialize(MinestomTools minestomTools, Type type, JsonSerializationContext jsonSerializationContext) {
        var object = new JsonObject();
        object.addProperty("item", minestomTools.getItem().name());
        object.addProperty("attackDamageValue", minestomTools.getAttackDamageValue());
        object.addProperty("legacyAttackDamageValue", minestomTools.getLegacyAttackDamageValue());
        object.addProperty("attackSpeedValue", minestomTools.getAttackSpeedValue());
        return object;
    }
}
