package org.example.hack4benagal2.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.example.hack4benagal2.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ChatService chatService;


    @PostMapping("/ask")
    public ResponseEntity<String> extractPdfText(
            @RequestParam("question") String msg,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("Invalid file. Please upload a PDF.");
        }

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            return chatService.extractPdfText(msg, text);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading PDF: " + e.getMessage());
        }
    }


    @GetMapping("/status")
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok("API is up and running!");
    }


    @PostMapping("/extract-text")
    public ResponseEntity<String> extractTextFromPdf(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("Invalid file. Please upload a PDF.");
        }

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            return ResponseEntity.ok(text);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading PDF: " + e.getMessage());
        }
    }


    @PostMapping("/ask-without-pdf")
    public ResponseEntity<String> askWithoutPdf(@RequestParam("question") String question) {
        return chatService.extractPdfText(question, "");
    }


    @PostMapping("/summary")
    public ResponseEntity<String> getPdfSummary(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("Invalid file. Please upload a PDF.");
        }

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            return chatService.summarizePdf(text);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading PDF: " + e.getMessage());
        }
    }
}
