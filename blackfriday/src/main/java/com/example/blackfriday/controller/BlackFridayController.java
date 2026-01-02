package com.example.blackfriday.controller;

import com.example.blackfriday.domain.Product;
import com.example.blackfriday.domain.dto.ProductSummary;
import com.example.blackfriday.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/black-friday")
@RequiredArgsConstructor
public class BlackFridayController {

    private final ProductRepository productRepository;

    @GetMapping("/fatal")
    public List<Product> getFullCatalogFatal() {
        System.out.println("\n🔥 [FATAL] INICIANDO CARGA DE CATÁLOGO COMPLETO (ENTIDADES) ...");

        return productRepository.findAll();
    }

    @GetMapping("/efficient")
    public List<ProductSummary> getCatalogEfficient() {
        System.out.println("\n🚀 [OPTIMIZED] CARGANDO SOLO RESUMEN DE PRODUCTOS ...");

        return productRepository.findCatalogSummary();
    }
}
