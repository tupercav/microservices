package com.tupercav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckHistoryService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Autowired
    public FraudCheckHistoryService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer (Integer customerId) {
        fraudCheckHistoryRepository.save(FraudCheckHistory
                        .builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build());
        return false;
    }
}
