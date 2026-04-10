package com.ns1903372dot.whatsapp.model;

import jakarta.validation.constraints.NotBlank;

public record WebhookRequest(
        @NotBlank(message = "from is required")
        String from,

        @NotBlank(message = "message is required")
        String message
) {
}
