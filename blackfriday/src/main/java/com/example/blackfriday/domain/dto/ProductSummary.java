package com.example.blackfriday.domain.dto;

public record ProductSummary(
        Long id,
        String name,
        double price,
        String mainImageUrl
) { }
