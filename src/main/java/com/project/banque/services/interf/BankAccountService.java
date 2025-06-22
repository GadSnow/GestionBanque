package com.project.banque.services.interf;

import com.project.banque.entities.CurrentAccount;
import com.project.banque.entities.SavingAccount;
import com.project.banque.repositories.CurrentAccountRepository;
import com.project.banque.repositories.SavingAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface BankAccountService {


    public CurrentAccount createCurrentAccount(CurrentAccount account) ;

    public SavingAccount createSavingAccount(SavingAccount account);
}
