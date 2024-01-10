package net.bytemc.pvp.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bytemc.pvp.Pvp;
import net.bytemc.pvp.tools.MinestomTools;
import net.bytemc.pvp.tools.MinestomToolsSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class GsonUtils {

    public static final Gson DEFAULT_GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(MinestomTools.class, new MinestomToolsSerializer()).create();

    public static <T> T readConfiguration(Pvp pvp, String ResourcesPath, Class<T> clazz) {
        try (var input = Objects.requireNonNull(pvp.getClass().getResource("/" + ResourcesPath)).openStream()) {
            var reader = new BufferedReader(new InputStreamReader(input));
            var stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            return GsonUtils.DEFAULT_GSON.fromJson(String.join("", stringBuilder.toString()), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
