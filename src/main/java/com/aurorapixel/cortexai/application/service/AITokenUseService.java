package com.aurorapixel.cortexai.application.service;

import com.aurorapixel.cortexai.domain.repository.ai.AITokenUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AITokenUseService {
    private final AITokenUserRepository aiTokenUserRepository;
}
