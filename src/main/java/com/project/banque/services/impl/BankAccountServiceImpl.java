package com.project.banque.services.impl;

import com.project.banque.entities.CurrentAccount;
import com.project.banque.entities.SavingAccount;
import com.project.banque.repositories.CurrentAccountRepository;
import com.project.banque.repositories.SavingAccountRepository;
import com.project.banque.services.interf.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final CurrentAccountRepository currentAccountRepository;
    private final SavingAccountRepository savingAccountRepository;


    public CurrentAccount createCurrentAccount(CurrentAccount account) {
        return currentAccountRepository.save(account);
    }

    public SavingAccount createSavingAccount(SavingAccount account) {
        return savingAccountRepository.save(account);
    }
}
