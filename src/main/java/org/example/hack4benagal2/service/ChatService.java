package org.example.hack4benagal2.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    public ResponseEntity<String> extractPdfText(String msg, String fileText) {
        String prompt = "Given the following PDF content:\n" + fileText + "\n\n"
                + "Answer this question:\n" + msg;

        return getChatResponse(prompt, "Error while processing the question");
    }

    public ResponseEntity<String> summarizePdf(String fileText) {
        String prompt = "Summarize the following PDF content:\n" + fileText;

        return getChatResponse(prompt, "Error while summarizing the PDF");
    }


    public ResponseEntity<String> askWithoutPdf(String question) {
        return getChatResponse(question, "Error while answering the question");
    }


    private ResponseEntity<String> getChatResponse(String prompt, String errorMsg) {
        try {
            ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
            if (response == null || response.getResult() == null || response.getResult().getOutput() == null) {
                return ResponseEntity.status(500).body("Model returned an incomplete response.");
            }
            return ResponseEntity.ok(response.getResult().getOutput().getText());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(errorMsg + ": " + e.getMessage());
        }
    }
}
