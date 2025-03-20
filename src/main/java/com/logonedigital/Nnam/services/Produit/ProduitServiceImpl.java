package com.logonedigital.Nnam.services.Produit;

import com.logonedigital.Nnam.dto.PdfExportConfigDTO;
import com.logonedigital.Nnam.dto.produit.ProduitReqDTO;
import com.logonedigital.Nnam.dto.produit.ProduitResDTO;
import com.logonedigital.Nnam.entities.Categorie;
import com.logonedigital.Nnam.entities.Produit;
import com.logonedigital.Nnam.entities.Stock;
import com.logonedigital.Nnam.exception.ResourceNotFoundException;
import com.logonedigital.Nnam.mapper.ProduitMapper;
import com.logonedigital.Nnam.mapper.StockMapper;
import com.logonedigital.Nnam.repository.CategorieRepo;
import com.logonedigital.Nnam.repository.ProduitRepo;
import com.logonedigital.Nnam.repository.StockRepo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    //private final TemplateEngine templateEngine;
    private final ProduitRepo produitRepository;
    private final CategorieRepo categorieRepository;
    private final StockRepo stockRepository;
    private final ProduitMapper produitMapper;
    private final StockMapper stockMapper;

    public ProduitServiceImpl(ProduitRepo produitRepository, CategorieRepo categorieRepository, StockRepo stockRepository, ProduitMapper produitMapper, StockMapper stockMapper) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.stockRepository = stockRepository;
        this.produitMapper = produitMapper;
        this.stockMapper = stockMapper;
    }

    @Override
    public Produit addProduit(ProduitReqDTO produitReqDTO) {
        // Vérifiez que la catégorie existe
        Categorie categorie = this.categorieRepository
                .findById(produitReqDTO.getCategorieId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produitReqDTO.getCategorieId()));

        //creons le stock
        Stock stock = new Stock();
        stock.setNom(produitReqDTO.getStock().getNom());
        stock.setQuantiteStock(produitReqDTO.getStock().getQuantiteStock());
        Stock savedStock = stockRepository.save(stock);
        //creons le produit
        Produit produit = produitMapper.getProduitFromProduitReqDTO(produitReqDTO);
        produit.setCategorie(categorie);
        produit.setStock(savedStock);

        return produitRepository.save(produit);

    }

    @Override
    public ProduitResDTO updateProduit(int idProduit, ProduitReqDTO produitReqDTO) {
        Produit existingProduit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit));

        // Mise à jour les champs du produit
        existingProduit.setNomProduit(produitReqDTO.getNomProduit());
        existingProduit.setDescription(produitReqDTO.getDescription());
        existingProduit.setPrixU(produitReqDTO.getPrixU());
        existingProduit.setDateExpiration(produitReqDTO.getDateExpiration());

        // Mise à jour la catégorie si elle est fournie sans ecraser ce qui etait deja la
        if (produitReqDTO.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(produitReqDTO.getCategorieId())
                    .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produitReqDTO.getCategorieId()));
            existingProduit.setCategorie(categorie);
        }

        // Mise à jour le stock si il est fourni
        if (produitReqDTO.getStock() != null) {
            Stock existingStock = existingProduit.getStock();
            existingStock.setNom(produitReqDTO.getStock().getNom());
            existingStock.setQuantiteStock(produitReqDTO.getStock().getQuantiteStock());
            stockRepository.save(existingStock); // MàJ sans créer de nouvelle entité
        }

        // Sauvegarde de produit mis à jour
        produitRepository.save(existingProduit);
        return produitMapper.getProduitResDTOFromProduit(existingProduit);
    }

    @Override
    public void deleteProduit(int idProduit) {
        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit));
        produitRepository.delete(produit);
        this.produitMapper.getProduitResDTOFromProduit(produit);
    }


    @Override
    public ProduitResDTO getProduit(int idProduit) {
        Produit produit = produitRepository.findProduitWithStock(idProduit);

        if (produit == null) {
            throw new ResourceNotFoundException("Produit non trouvé avec l'ID : " + idProduit);
        }

        ProduitResDTO dto = produitMapper.getProduitResDTOFromProduit(produit);

        if (produit.getStock() != null) {
            dto.setStock(stockMapper.toDTO(produit.getStock())); // Plus jamais NULL !
        }

        return dto;
    }

    @Override
    public List<ProduitResDTO> getAllProduits() {
        List<Produit> produits = this.produitRepository.findAll();
        return this.produitMapper.toDtoProduitList(produits);
    }

    @Override
    public Page<Produit> getAllProduit(Pageable pageable) {
        return produitRepository.findAll(pageable);
    }

    @Override
    public List<Produit> search(String nom, Double minPrice, Double maxPrice) {
        if (nom != null && minPrice != null) {
            return produitRepository.findByNomProduitContainingAndPrixUBetween(nom, minPrice, maxPrice);
        }
        return produitRepository.findAll();
    }


/*@Override
    public byte[] generateProduitsPdfReport(PdfExportConfigDTO config) throws Exception {
        // Récupérer les données
        List<Produit> produits = produitRepository.findAll();

        // Préparer le contexte Thymeleaf
        Context context = new Context();
        context.setVariable("produits", produits);
        context.setVariable("config", config);

        // Générer le HTML
        String htmlContent = templateEngine.process("products-pdf-template", context);

        // Convertir en PDF
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
  *
@Override
public byte[] generateProduitsPdfReport(PdfExportConfigDTO config) throws Exception {
    List<Produit> produits = produitRepository.findAll();

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         PDDocument document = new PDDocument()) {

        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // En-tête
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Rapport des Produits");
            contentStream.endText();

            // Contenu
            int y = 650;
            for (Produit p : produits) {
                String line = String.format("%-20s %-10s %-10s",
                        p.getNomProduit(),
                        p.getPrixU(),
                        config.isIncludeStockDetails() ? p.getStock().getQuantiteStock() : "");

                contentStream.beginText();
                contentStream.newLineAtOffset(100, y);
                contentStream.showText(line);
                contentStream.endText();

                y -= 20;
            }
        }

        document.save(outputStream);
        return outputStream.toByteArray();
        }
    }*/

    @Override
    public byte[] generateProduitsPdfReport(PdfExportConfigDTO config) throws Exception {
        List<Produit> produits = produitRepository.findAll();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = null;
            float y = 700;
            float margin = 50;
            float[] columnXpositions = {margin, margin + 200, margin + 350};

            try {
                contentStream = new PDPageContentStream(document, page);

                // En-tête personnalisé
                if (config.getHeaderText() != null) {
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, y);
                    contentStream.showText(config.getHeaderText());
                    contentStream.endText();
                    y -= 30;
                }
                // En-tête

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                drawColumnText(contentStream, columnXpositions, y,
                        new String[]{"Nom Produit", "Prix (FCFA)", "Stock"});

                y -= 30;

                // Données
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                for (Produit p : produits) {
                    if (y < 100) {
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        y = 700;

                        // Réinitialiser la police après nouvelle page
                        contentStream.setFont(PDType1Font.HELVETICA, 12);
                    }

                    // Formatter le prix avec des caractères valides
                    String prixFormatted = String.format(java.util.Locale.US, "%,.2f", p.getPrixU())
                            .replace('\u202F', ' '); // Remplace l'espace insécable

                    drawColumnText(contentStream, columnXpositions, y,
                            new String[]{
                                    p.getNomProduit(),
                                    prixFormatted,
                                    String.valueOf(p.getStock().getQuantiteStock())
                            });

                    y -= 20;
                }

                if (config.getFooterText() != null) {
                    contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, 40); // Position Y fixe en bas
                    contentStream.showText(config.getFooterText());
                    contentStream.endText();
                }
            } finally {
                if (contentStream != null) {
                    contentStream.close();
                }
            }

            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }

    // Méthode helper inchangée
    private void drawColumnText(PDPageContentStream cs, float[] positions, float y, String[] texts)
            throws IOException {
        for (int i = 0; i < texts.length; i++) {
            cs.beginText();
            cs.newLineAtOffset(positions[i], y);
            cs.showText(texts[i]);
            cs.endText();
        }
    }
}