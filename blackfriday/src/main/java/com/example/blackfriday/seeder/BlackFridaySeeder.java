package com.example.blackfriday.seeder;

import com.example.blackfriday.domain.*;
import com.example.blackfriday.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class BlackFridaySeeder {

    @Bean
    public CommandLineRunner initBlackFridayData(ProductRepository productRepository) {
        return args -> {
            // Verificar si ya hay datos para no duplicar
            if (productRepository.count() > 0) return;

            System.out.println("🛍️ --- INICIANDO CARGA DE DATOS BLACK FRIDAY ---");

            // Crear 50 productos
            for (int i = 1; i <= 50; i++) {
                Product p = new Product();
                p.setName("Producto Top Tier #" + i);
                p.setPrice(100.00 + i);

                // Cada producto tiene 5 imágenes
                for (int j = 1; j <= 5; j++) {
                    ProductImage img = new ProductImage();
                    img.setUrl("http://img.com/" + i + "/" + j + ".jpg");
                    p.addImage(img);
                }

                // Cada producto tiene 10 variantes (S-Red, M-Blue, etc)
                for (int k = 1; k <= 10; k++) {
                    ProductVariant v = new ProductVariant();
                    v.setSize("Size-" + k);
                    v.setColor("Color-" + k);
                    p.addVariant(v);
                }

                // Cada producto tiene 20 Reviews
                for (int l = 1; l <= 20; l++) {
                    ProductReview r = new ProductReview();
                    r.setUserComment("Excelente producto " + i);
                    r.setRating(5);
                    p.addReview(r);
                }

                productRepository.save(p);
            }

            System.out.println("✅ DATAZO: 50 Productos creados.");
            System.out.println("   -> Total esperado de filas hijas: 250 imgs, 500 variants, 1000 reviews.");
        };
    }
}
