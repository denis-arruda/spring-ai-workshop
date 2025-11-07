package com.denisarruda.spring_ai_workshop.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat() {
        return chatClient.prompt()
                .user("Tell me an interesting fact about Java")
                .call()
                .content();
    }

    @GetMapping(value = "/stream")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("I will visit Paris soon. can you give me 10 places to visit?")
                .stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse joke() {
        return chatClient.prompt()
                .user("Tell me a dad joke about programming")
                .call()
                .chatResponse();
    }
}
