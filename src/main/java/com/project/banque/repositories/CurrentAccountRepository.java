package com.project.banque.repositories;

import com.project.banque.entities.CurrentAccount;
import com.project.banque.entities.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {
}
