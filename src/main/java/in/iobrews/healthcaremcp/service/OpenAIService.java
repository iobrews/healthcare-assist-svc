package in.iobrews.healthcaremcp.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class OpenAIService {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    private final WebClient webClient;

    public OpenAIService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + API_KEY)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String getResponse(String prompt){
        Map<String, Object> requestPayload = Map.of(
                "model", "gpt-4",
                "messages", List.of(
                    Map.of("role", "system", "content", "You are a virtual assistant helping Dr. smith with a patient consultation"),
                    Map.of("role", "user", "content", prompt))
        );


        return webClient.post()
                    .bodyValue(requestPayload)
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorResume(ex -> {
                        return Mono.just("Error occurred while processing the request. "+ex.getMessage());
                    })
                    .block();
    }


}
