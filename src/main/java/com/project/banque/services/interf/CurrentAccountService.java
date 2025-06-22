package com.project.banque.services.interf;

import com.project.banque.entities.CurrentAccount;

import java.util.List;

public interface CurrentAccountService {

    CurrentAccount create(CurrentAccount account);
    CurrentAccount getById(Long id);
    List<CurrentAccount> getAll();
    CurrentAccount update(Long id, CurrentAccount updatedAccount);
    void delete(Long id);
}
