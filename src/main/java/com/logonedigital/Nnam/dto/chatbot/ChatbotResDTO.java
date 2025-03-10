package com.logonedigital.Nnam.dto.chatbot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotResDTO {
    private String response;
    private List<?> data; // Résultats génériques (Produits, Catégories, Stocks)
}
