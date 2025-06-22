package com.project.banque.entities;

import com.project.banque.enums.AccountStatus;
import com.project.banque.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
public class CurrentAccount extends BankAccount {

}