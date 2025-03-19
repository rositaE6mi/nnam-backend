package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.dto.chatbot.ChatbotReqDTO;
import com.logonedigital.Nnam.dto.chatbot.ChatbotResDTO;
import com.logonedigital.Nnam.services.chatbot.ChatbotService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public class ChatbotController {
    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/ask")
    public ResponseEntity<ChatbotResDTO> askChatbot(@Valid @RequestBody ChatbotReqDTO request) {
        return ResponseEntity.ok(chatbotService.processMessage(request));
    }
}
