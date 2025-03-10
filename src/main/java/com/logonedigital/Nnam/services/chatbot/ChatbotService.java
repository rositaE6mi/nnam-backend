package com.logonedigital.Nnam.services.chatbot;

import com.logonedigital.Nnam.dto.chatbot.ChatbotReqDTO;
import com.logonedigital.Nnam.dto.chatbot.ChatbotResDTO;

public interface ChatbotService {
    ChatbotResDTO processMessage(ChatbotReqDTO request);
    String extractParam(String message, String key);



}
