package com.denisarruda.spring_ai_workshop.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class VacationPlan {

    private final ChatClient chatClient;

    public VacationPlan(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("vacation/unstructured")
    public String unstructured() {
        return chatClient.prompt()
                .user("I want a to plan a trip to Hawaii. Give me a list of things to do.")
                .call()
                .content();
    }

    @GetMapping("vacation/structured")
    public Itinerary structured() {
        return chatClient.prompt()
                .user("I want a to plan a trip to Hawaii. Give me a list of things to do.")
                .call()
                .entity(Itinerary.class);
    }
}
