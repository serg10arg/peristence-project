package com.example.frauddetectionservice.controller;

import com.example.frauddetectionservice.domain.SuspiciousActivityDTO;
import com.example.frauddetectionservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fraud")
@RequiredArgsConstructor
public class FraudController {

    private final AccountRepository accountRepository;

    @GetMapping("/scan")
    public List<SuspiciousActivityDTO> scanForMules() {
        return accountRepository.findSuspiciousAccounts(
                LocalDateTime.now().minusHours(24),
                new BigDecimal("10.00"),
                5L
        );
    }
}
