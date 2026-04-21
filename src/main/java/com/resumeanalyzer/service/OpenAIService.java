package com.resumeanalyzer.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final MediaType JSON = MediaType.parse("application/json");

    public String analyze(String resumeText, String jobDesc) throws Exception {

        String prompt = "Compare resume with job description and return ATS score, missing keywords, suggestions.\n\nResume:\n"
                + resumeText + "\n\nJob:\n" + jobDesc;

        OkHttpClient client = new OkHttpClient();

        // Build JSON safely using Map
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "gpt-4o-mini");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        requestMap.put("messages", new Object[]{message});

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(requestMap);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(RequestBody.create(body, JSON))
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new RuntimeException("OpenAI API error: " + response.code() + " " + response.message());
            }

            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new RuntimeException("Empty response body");
            }

            return responseBody.string();
        }
    }
}