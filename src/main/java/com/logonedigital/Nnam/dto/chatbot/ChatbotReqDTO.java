package com.logonedigital.Nnam.dto.chatbot;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotReqDTO {
    @NotBlank(message = "Le message ne peut pas être vide")
    private String message;
}
