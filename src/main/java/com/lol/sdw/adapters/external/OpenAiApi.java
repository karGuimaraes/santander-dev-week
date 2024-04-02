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

@ConditionalOnProperty(name = "generative-ai.provaider", havingValue = "OPENAI", matchIfMissing = true)
@FeignClient(name = "openAiApi", url = "${openai.base-url}", configuration = OpenAiApi.Config.class)
public interface OpenAiApi extends GenerativeAiApi{

    @PostMapping("/v1/chat/completions")
    OpenAiCompletionResponse chatCompletion(OpenAiCompletionRequest request);

    @Override
    default String generateContent(String objective, String context) {
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
            new Message("system", objective),
            new Message("user", context)
        );
        OpenAiCompletionRequest request = new OpenAiCompletionRequest(model, messages);
        try {
            OpenAiCompletionResponse response = chatCompletion(request);
            return response.choices().getFirst().message().content();
        } catch (FeignException httpErrors) {
            throw new ResponseStatusException(httpErrors.status(), "Error on OpenAI API: " + httpErrors.getMessage(), httpErrors);
        }catch (Exception e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }
      
    record OpenAiCompletionRequest(String model, List<Message> messages) {}
    record Message(String role, String content) {}

    record OpenAiCompletionResponse(List<Choice> choices) {}
    record Choice(Message message) {}

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return template -> template.header("Authorization", "Bearer %s".formatted(apiKey));
        }
    }
}
