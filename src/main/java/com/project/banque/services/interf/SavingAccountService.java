package com.project.banque.services.interf;

import com.project.banque.entities.SavingAccount;

import java.util.List;

public interface SavingAccountService {

    SavingAccount create(SavingAccount account);
    SavingAccount getById(Long id);
    List<SavingAccount> getAll();
    SavingAccount update(Long id, SavingAccount updatedAccount);
    void delete(Long id);
}
