package com.ns1903372dot.whatsapp.service;

import com.ns1903372dot.whatsapp.model.WebhookRequest;
import com.ns1903372dot.whatsapp.model.WebhookResponse;
import java.util.Locale;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    private static final Map<String, String> REPLIES = Map.of(
            "hi", "Hello",
            "bye", "Goodbye"
    );

    public WebhookResponse buildResponse(WebhookRequest request) {
        String normalizedMessage = request.message().trim().toLowerCase(Locale.ROOT);
        String reply = REPLIES.getOrDefault(
                normalizedMessage,
                "Sorry, I only understand messages like Hi or Bye."
        );

        return new WebhookResponse(request.from(), request.message(), reply);
    }
}
