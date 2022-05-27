package com.fala.challenge.fixture;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ReadFile {
    public String readFile(String fileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw e;
        }

    }


}
