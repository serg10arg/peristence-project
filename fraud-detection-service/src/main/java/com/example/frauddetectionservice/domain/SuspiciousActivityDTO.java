package com.example.frauddetectionservice.domain;


import java.math.BigDecimal;

public record SuspiciousActivityDTO(
        String ownerName,
        Long transactionsCount,
        BigDecimal currentBalance
) {}
