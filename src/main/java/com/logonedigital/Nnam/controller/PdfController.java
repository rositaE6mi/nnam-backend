package com.logonedigital.Nnam.controller;

import com.logonedigital.Nnam.services.PdfService.PdfService; // Vérifie cet import
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/facture/{commandeId}")
    public ResponseEntity<byte[]> downloadFacturePdf(@PathVariable Integer commandeId) {
        System.out.println("📝 Génération de la facture pour la commande ID : " + commandeId);
        try {
            byte[] pdfBytes = pdfService.generateFacturePdf(commandeId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture_" + commandeId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (RuntimeException e) {
            System.out.println("🚨 Erreur : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage().getBytes());
        } catch (Exception e) {
            System.out.println("🔥 Erreur serveur lors de la génération du PDF !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la génération du PDF".getBytes());
        }
    }

}