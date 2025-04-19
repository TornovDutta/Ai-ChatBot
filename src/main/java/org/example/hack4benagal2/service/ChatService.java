package org.example.hack4benagal2.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private ChatClient chatClient;

    public ChatService(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }
    public ResponseEntity<String> extractPdfText(String msg, String fileText) {
        String combinedText = "get the information from this file " + fileText + "\n" + "and this question " + msg;

        try {
            ChatResponse response = chatClient.prompt(combinedText).call().chatResponse();
            if (response == null || response.getResult() == null || response.getResult().getOutput() == null) {
                return ResponseEntity.status(500).body("Model returned an incomplete response.");
            }

            String message = response.getResult().getOutput().getText();
            return ResponseEntity.ok(message);

        } catch (Exception e) {
            e.printStackTrace(); // Log for debugging
            return ResponseEntity.status(500).body("Error while processing the question: " + e.getMessage());
        }
    }

}
