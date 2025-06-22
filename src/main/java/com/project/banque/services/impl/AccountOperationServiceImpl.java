package com.project.banque.services.impl;

import com.project.banque.entities.AccountOperation;
import com.project.banque.repositories.AccountOperationRepository;
import com.project.banque.services.interf.AccountOperationService;
import com.project.banque.services.interf.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountOperationServiceImpl implements AccountOperationService {

    final AccountOperationRepository accountOperationRepository;

    @Override
    public AccountOperation createAccountOperation(AccountOperation accountOperation) {
        return accountOperationRepository.save(accountOperation);
    }
}
