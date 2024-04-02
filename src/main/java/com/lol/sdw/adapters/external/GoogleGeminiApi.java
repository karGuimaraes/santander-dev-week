package com.lol.sdw.adapters.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.lol.sdw.domain.champions.ports.GenerativeAiApi;

import feign.FeignException;
import feign.RequestInterceptor;

@ConditionalOnProperty(name = "generative-ai.provaider", havingValue = "GEMINI")
@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GoogleGeminiApi.Config.class)
public interface GoogleGeminiApi extends GenerativeAiApi{

    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GoogleGeminiApiResponse textOnlyInput(GoogleGeminiApiRequest request);

    @Override
    default String generateContent(String objective, String context) {
        String prompt = """
                %s
                %s
                """.formatted(objective, context);
        GoogleGeminiApiRequest request = new GoogleGeminiApiRequest(
            List.of(new Content(List.of(new Part(prompt))))
        );
        try {
            GoogleGeminiApiResponse response = textOnlyInput(request);
            return response.candidates().getFirst().content().parts().getFirst().text();

        } catch (FeignException httpErrors) {
            throw new ResponseStatusException(httpErrors.status(), "Error on Google Gemini API: " + httpErrors.getMessage(), httpErrors);
        }catch (Exception e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }
      
    record GoogleGeminiApiRequest(List<Content> contents) {}
    record Content(List<Part> parts) {}
    record Part(String text) {}

    record GoogleGeminiApiResponse(List<Candidate> candidates) {}
    record Candidate(Content content) {}

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey) {
            return template -> template.query("key", apiKey);
        }
    }
}
