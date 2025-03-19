package com.logonedigital.Nnam.dto.chatbot;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotResDTO {
    private String response;
    private List<?> data; // Résultats génériques (Produits, Catégories, Stocks)
}
