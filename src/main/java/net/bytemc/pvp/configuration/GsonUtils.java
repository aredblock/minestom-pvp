package net.bytemc.pvp.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import net.bytemc.pvp.Pvp;
import net.bytemc.pvp.ToolConfiguation;
import net.bytemc.pvp.tools.MinestomTools;
import net.bytemc.pvp.tools.MinestomToolsSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class GsonUtils {

    public static final Gson DEFAULT_GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(MinestomTools.class, new MinestomToolsSerializer()).create();

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T> T readConfiguration(Path path, T value) {
        if (!Files.exists(path)) {
            Files.write(path, GsonUtils.DEFAULT_GSON.toJson(value).getBytes());
            Files.createFile(path);
            return value;
        } else {
            return (T) GsonUtils.DEFAULT_GSON.fromJson(String.join("", Files.readAllLines(path)), value.getClass());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readConfiguration(Pvp pvp, String ResourcesPath, Class<T> clazz) {
        try (var input = Objects.requireNonNull(pvp.getClass().getResource("/" + ResourcesPath)).openStream()) {
            var reader = new BufferedReader(new InputStreamReader(input));
            var stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            return (T) GsonUtils.DEFAULT_GSON.fromJson(String.join("", stringBuilder.toString()), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
