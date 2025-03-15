package com.logonedigital.Nnam.dto.chatbot;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotReqDTO {
    @NotBlank(message = "Le message ne peut pas être vide")
    private String message;
}
