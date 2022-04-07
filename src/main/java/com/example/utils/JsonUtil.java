package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectWriter writer = mapper.writer();

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();;



    private static final String PATH_PREFIX = "src/main/resources";

    private static String readFileString(String resourcePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String absolutePath = new File(PATH_PREFIX + resourcePath).getAbsolutePath();
        Files.readAllLines(Paths.get(absolutePath))
             .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private static void writeToFile(String resourcePath, String value) throws IOException {
        String absolutePath = new File(PATH_PREFIX + resourcePath).getAbsolutePath();
        Files.writeString(Paths.get(absolutePath), value);
    }

    public static <T> ArrayList<T> parseCollectionJson(String resourcePath, Class<T> clazz) throws IOException {

        CollectionType listType =
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);

        return mapper.readValue(new File(PATH_PREFIX+resourcePath),listType);
    }

    public static <T> void writeJson(String resourcePath, T object) throws IOException {
        writeToFile(resourcePath, writer.writeValueAsString(object));
    }



}

class LocalDateAdapter implements JsonSerializer<LocalDate> {

    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        JsonPrimitive jsonPrimitive = new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return jsonPrimitive; // "yyyy-mm-dd"
    }
}
