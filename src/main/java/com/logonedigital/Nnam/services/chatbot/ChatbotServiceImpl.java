package com.logonedigital.Nnam.services.chatbot;

import com.logonedigital.Nnam.dto.chatbot.ChatbotReqDTO;
import com.logonedigital.Nnam.dto.chatbot.ChatbotResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.entities.Stock;
import com.logonedigital.Nnam.exception.InvalidRequestException;
import com.logonedigital.Nnam.services.Categorie.CategorieService;
import com.logonedigital.Nnam.services.Produit.ProduitService;
import com.logonedigital.Nnam.services.Stock.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ChatbotServiceImpl implements ChatbotService {
    private final ProduitService produitService;
    private final CategorieService categorieService;
    private final StockService stockService;

    public ChatbotServiceImpl(ProduitService produitService, CategorieService categorieService, StockService stockService) {
        this.produitService = produitService;
        this.categorieService = categorieService;
        this.stockService = stockService;
    }

    @Override
    public ChatbotResDTO processMessage(ChatbotReqDTO request) {
        String message = request.getMessage().toLowerCase();
        ChatbotResDTO response = new ChatbotResDTO();

        try {
            if (message.contains("produit")) {
                Map<String, String> params = extractParams(message);
                List<Produit> produits = produitService.search(
                        params.get("nom"),
                        params.containsKey("prixMin") ? Double.parseDouble(params.get("prixMin")) : null,
                        params.containsKey("prixMax") ? Double.parseDouble(params.get("prixMax")) : null
                        // ... autres paramètres
                );
                response.setData(produits);
                response.setResponse("✅ " + produits.size() + " produits trouvés.");
            } else if (message.contains("categorie")) {
                List<Categorie> categories = categorieService.searchCategories(
                        extractParam(message, "nom"),
                        extractParam(message, "description"),
                        extractParamAsInt(message, "minProduits")
                );
                response.setData(categories);
                response.setResponse("✅ " + categories.size() + " catégories trouvées.");
            } else if (message.contains("stock")) {
                List<Stock> stocks = stockService.searchStocks(
                        extractParam(message, "nom"),
                        extractParamAsInt(message, "minQuantite"),
                        extractParamAsInt(message, "maxQuantite")
                );
                response.setData(stocks);
                response.setResponse("✅ " + stocks.size() + " stocks trouvés.");
            } else {
                throw new InvalidRequestException("Commande non reconnue ❌");
            }
        } catch (Exception e) {
            response.setResponse("⚠️ Erreur: " + e.getMessage());
        }

        return response;
    }

    // Extraction des paramètres (ex: "nom=electronique prixMin=100")
    private Map<String, String> extractParams(String message) {
        return Pattern.compile("(\\w+)=([^\\s]+)")
                .matcher(message)
                .results()
                .collect(Collectors.toMap(
                        m -> m.group(1),
                        m -> m.group(2)
                ));
    }

    @Override
    public String extractParam(String message, String key) {
        Pattern pattern = Pattern.compile(key + "=([^\\s]+)");
        Matcher matcher = pattern.matcher(message);
        return matcher.find() ? matcher.group(1) : null;
    }

    private Integer extractParamAsInt(String message, String key) {
        String value = extractParam(message, key);
        return value != null ? Integer.parseInt(value) : null;
    }
}