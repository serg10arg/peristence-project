package com.example.blackfriday.repository;

import com.example.blackfriday.domain.Product;
import com.example.blackfriday.domain.dto.ProductSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT DISTINCT new com.example.blackfriday.domain.dto.ProductSummary(
            p.id,
            p.name,
            p.price,
            (SELECT i.url FROM ProductImage i WHERE i.product = p ORDER BY i.id ASC LIMIT 1)
        )
        FROM Product p
    """)
    List<ProductSummary> findCatalogSummary();
}
