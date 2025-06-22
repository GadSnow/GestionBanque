package com.project.banque.services.impl;

import com.project.banque.entities.SavingAccount;
import com.project.banque.repositories.SavingAccountRepository;
import com.project.banque.services.interf.SavingAccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

    private final SavingAccountRepository repository;

    public SavingAccountServiceImpl(SavingAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public SavingAccount create(SavingAccount account) {
        return repository.save(account);
    }

    @Override
    public SavingAccount getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compte courant non trouvé avec l'id: " + id));
    }

    @Override
    public List<SavingAccount> getAll() {
        return repository.findAll();
    }

    @Override
    public SavingAccount update(Long id, SavingAccount updatedAccount) {
        SavingAccount existing = getById(id);
        existing.setBalance(updatedAccount.getBalance());
        existing.setStatus(updatedAccount.getStatus());
        existing.setType(updatedAccount.getType());
        // autres champs si besoin
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
