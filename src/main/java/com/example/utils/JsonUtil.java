package com.example.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {

    private static final Gson gson = new Gson();

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

    public static <T> T parseJson(String resourcePath, Class<T> clazz) throws IOException {
        return gson.fromJson(readFileString(resourcePath), clazz);
    }

    public static <T> void writeJson(String resourcePath, T object) throws IOException {
        writeToFile(resourcePath, gson.toJson(object));
    }

    public static <T> T parseJson(String resourcePath, TypeToken<T> typeToken) throws IOException {
        return gson.fromJson(readFileString(resourcePath), typeToken.getType());
    }

}
