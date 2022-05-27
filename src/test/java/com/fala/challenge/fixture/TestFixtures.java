package com.fala.challenge.fixture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


public class TestFixtures {

    private static final Map<String, String> tracingHeaders = new HashMap<>();
    private static final Map<String, String> eventHeaders = new HashMap<>();
    private static ReadFile file = new ReadFile();


    public static MockHttpServletRequestBuilder buildPostRequest(String fileName, String url) throws IOException {
        String json = file.readFile(fileName);
        return addHeaders(post(url).content(json));
    }

    public static MockHttpServletRequestBuilder buildPostRequestWithoutBody(String url) {
        return addHeaders(post(url).content("null"));
    }

    public static MockHttpServletRequestBuilder buildPutRequest(String fileName, String url) throws IOException {
        String json = file.readFile(fileName);
        return addHeaders(put(url).content(json));
    }


    public static MockHttpServletRequestBuilder buildGetRequest(String url) {
        return addHeaders(get(url));
    }

    public static MockHttpServletRequestBuilder buildDeleteRequest(String url) {
        return addHeaders(delete(url));
    }

    public static MockHttpServletRequestBuilder addHeaders(MockHttpServletRequestBuilder requestBuilder) {
        tracingHeaders.put("X_TENANT_ID", "123");
        return requestBuilder
                .header("AUTHORIZATION", "token")
                .requestAttr("tracingHeaders", tracingHeaders)
                .requestAttr("eventHeaders", eventHeaders)
                .contentType(APPLICATION_JSON);
    }

    public static <T> T buildObjectMapper(String jsonPathFile, Class<T> valueType) throws IOException {
        String json = file.readFile(jsonPathFile);

        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();

        // convert JSON file to map
        return mapper.readValue(json, valueType);

    }

}
