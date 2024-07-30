package com.aurorapixel.cortexai.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMessageDTO {
    @NotEmpty
    private String content;
    @NotEmpty
    private String conversationId;
    @NotNull
    @Min(1)
    private Long userId;
}
