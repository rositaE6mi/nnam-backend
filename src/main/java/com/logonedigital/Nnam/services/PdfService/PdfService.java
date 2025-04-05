package com.logonedigital.Nnam.services.PdfService;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.logonedigital.Nnam.entities.Commande;
import com.logonedigital.Nnam.entities.Facture;
import com.logonedigital.Nnam.entities.LigneCommande;
import com.logonedigital.Nnam.repository.CommandeRepo;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service // Assure-toi que cette annotation est présente
public class PdfService {

    private final CommandeRepo commandeRepo;
    private static final AtomicInteger factureCounter = new AtomicInteger(1);

    public PdfService(CommandeRepo commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    public byte[] generateFacturePdf(Integer commandeId) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.out.println("🔎 Recherche de la commande avec ID : " + commandeId);
        Commande commande = commandeRepo.findById(commandeId)
                .orElseThrow(() -> {
                    System.out.println("🚨 Commande non trouvée pour ID : " + commandeId);
                    return new RuntimeException("Commande non trouvée avec ID : " + commandeId);
                });

        System.out.println("✅ Commande trouvée : " + commande.getReference());

        Facture facture = commande.getFacture();
        if (facture == null) {
            throw new RuntimeException("Aucune facture associée à cette commande !");
        }

        try {
            outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // 🔹 Numéro unique de facture
            String dateFacture = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
            int numeroFacture = factureCounter.incrementAndGet();

            // ✅ En-tête
            document.add(new Paragraph("Gilles lemaire")
                    .setBold().setFontSize(18));
            document.add(new Paragraph("Lemaire, 62 37659 Biyem-assi\nTéléphone: +237 690145368\nEmail: lemairegilles@gmail.com")
                    .setFontSize(10).setTextAlignment(TextAlignment.RIGHT));

            // ✅ Facture & Numéro
            document.add(new Paragraph("Facture")
                    .setBold().setFontSize(24));
            document.add(new Paragraph(dateFacture + " | No." + numeroFacture)
                    .setFontSize(12).setBold());

            // ✅ Détails de la facture
            Date dateFacturation = facture.getDateFacturation();
            if (dateFacturation == null) {
                System.out.println("⚠️ La date de facturation est null. Attribution de la date actuelle.");
                // Si la date de facturation est null, on attribue la date actuelle
                dateFacturation = new Date();
            }

            document.add(new Paragraph("\nPAYMENT ÉCHU : " + new SimpleDateFormat("dd MMMM yyyy").format(dateFacturation)));
            document.add(new Paragraph("NUMÉRO DE DEMANDE : " + commande.getReference()));
            document.add(new Paragraph("TOTALE : " + facture.getMontantTotal() + " €"));

            // ✅ Tableau des lignes de commande
            Table table = new Table(new float[]{1, 4, 5, 1, 1, 1});
            table.setWidth(100);
            table.addCell(new Cell().add(new Paragraph("NO").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph("TYPE D’ARTICLE").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph("DESCRIPTION").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph("QNT").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph("PRIX").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph("P.T").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));

            int index = 1;
            for (LigneCommande ligne : commande.getLigneCommande()) {
                table.addCell(String.valueOf(index++));
                table.addCell("Nom du service/article");
                table.addCell("Description d'article/service ici");
                table.addCell(String.valueOf(ligne.getQuantite()));
                table.addCell(String.valueOf(ligne.getPrixUnitaire()) + " FCFA");
                table.addCell(String.valueOf(ligne.getTotalLigne()) + " FCFA");
            }
            document.add(table);

            // ✅ Totaux
            document.add(new Paragraph("\nFACTURE TOTALE:").setBold().setFontSize(14));
            document.add(new Paragraph("SUBTOTAL : " + facture.getMontantTotal() + " FCFA"));
            document.add(new Paragraph("TVA (21%) : " + (facture.getMontantTotal() * 0.21) + " FCFA"));
            document.add(new Paragraph("RÉDUCTION (10%) : " + (facture.getMontantTotal() * 0.1) + " FCFA"));
            document.add(new Paragraph("TOTALE : " + (facture.getMontantTotal() * 1.11) + " FCFA").setBold());

            // ✅ Conditions
            document.add(new Paragraph("\nTERMS ET CONDITIONS").setBold().setFontSize(12));
            document.add(new Paragraph("Auentiumunt undit accus et aut magnis a solorum autas digento con remantum sed estisum."));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
