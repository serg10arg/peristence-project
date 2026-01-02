package com.example.frauddetectionservice.repository;

import com.example.frauddetectionservice.domain.Account;
import com.example.frauddetectionservice.domain.SuspiciousActivityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("""
            SELECT new com.example.frauddetectionservice.domain.SuspiciousActivityDTO(
                a.ownerName,
                COUNT(t),
                a.currentBalance
                )
            FROM Account a
            JOIN a.transactions t
            WHERE 
                t.timestamp >= :since
                AND a.currentBalance < :maxBalance
            GROUP BY
                a.id, a.ownerName, a.currentBalance
            HAVING 
                COUNT(t) > :minTransactionCount
    """)
    List<SuspiciousActivityDTO> findSuspiciousAccounts(
            @Param("since") LocalDateTime since,
            @Param("maxBalance") BigDecimal maxBalance,
            @Param("minTransactionCount") Long minTransactionCount

    );
}
