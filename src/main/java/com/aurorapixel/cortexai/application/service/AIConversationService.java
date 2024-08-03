package com.aurorapixel.cortexai.application.service;

import com.aurorapixel.cortexai.domain.repository.ai.AIConversationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AIConversationService {
    private final AIConversationRepository aiConversationRepository;
}
