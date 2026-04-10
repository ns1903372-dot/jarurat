package com.ns1903372dot.whatsapp.controller;

import com.ns1903372dot.whatsapp.model.WebhookRequest;
import com.ns1903372dot.whatsapp.model.WebhookResponse;
import com.ns1903372dot.whatsapp.service.ChatbotService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    private final ChatbotService chatbotService;

    public WebhookController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
    public ResponseEntity<WebhookResponse> receiveMessage(@Valid @RequestBody WebhookRequest request) {
        logger.info(
                "Incoming WhatsApp message from={} message={}",
                request.from(),
                request.message()
        );

        WebhookResponse response = chatbotService.buildResponse(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
