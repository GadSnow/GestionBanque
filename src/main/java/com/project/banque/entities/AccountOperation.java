package com.project.banque.entities;

import com.project.banque.enums.AccountType;
import com.project.banque.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AccountOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime operationDate;

    @Column(nullable = false, unique = true)
    private String reference;

    // ✅ Lien unique vers BankAccount (CurrentAccount ou SavingAccount)
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccount;
}