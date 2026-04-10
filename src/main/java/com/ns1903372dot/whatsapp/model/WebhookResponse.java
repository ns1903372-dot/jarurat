package com.ns1903372dot.whatsapp.model;

public record WebhookResponse(
        String from,
        String receivedMessage,
        String reply
) {
}
