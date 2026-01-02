package com.example.frauddetectionservice.seeder;

import com.example.frauddetectionservice.domain.Account;
import com.example.frauddetectionservice.domain.Transaction;
import com.example.frauddetectionservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
@Profile("audit")
public class FraudDataSeeder {

    @Bean
    public CommandLineRunner initData(AccountRepository repo) {
        return args -> {
            if (repo.count() > 0) return;
            System.out.println("🕵️‍♂️ --- GENERANDO ESCENARIOS DE FRAUDE ---");

            // 1. CULPABLE: Mula Juan (Saldo bajo, Muchas tx recientes)
            createCase(repo, "Mula Juan", "5.00", 6, 0);

            // 2. INOCENTE: Honesto Pedro (Saldo alto)
            createCase(repo, "Honesto Pedro", "5000.00", 20, 0);

            // 3. INOCENTE: Pobre Inactivo (Pocas tx)
            createCase(repo, "Pobre Inactivo", "2.00", 2, 0);

            // 4. INOCENTE: Fraude Viejo (Fechas viejas)
            createCase(repo, "Viejo Truco", "1.00", 10, 30);

            System.out.println("✅ DATOS LISTOS EN DB (PUERTO 5435)");
        };
    }

    private void createCase(AccountRepository repo, String name, String balance, int txCount, int daysAgo) {
        Account acc = new Account();
        acc.setOwnerName(name);
        acc.setCurrentBalance(new BigDecimal(balance));

        for (int i = 0; i < txCount; i++) {
            Transaction tx = new Transaction();
            tx.setAmount(new BigDecimal("100"));
            tx.setTimestamp(LocalDateTime.now().minusDays(daysAgo));
            tx.setAccount(acc);
            acc.getTransactions().add(tx);
        }
        repo.save(acc);
    }
}