package com.aurorapixel.cortexai.application.service;

import com.aurorapixel.cortexai.domain.repository.ai.AIConversationMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AIConversationMessageService {
    private final AIConversationMessageRepository aiConversationMessageRepository;
}
