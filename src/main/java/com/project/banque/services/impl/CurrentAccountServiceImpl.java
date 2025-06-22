package com.project.banque.services.impl;

import com.project.banque.entities.CurrentAccount;
import com.project.banque.repositories.CurrentAccountRepository;
import com.project.banque.services.interf.CurrentAccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {


    private final CurrentAccountRepository repository;

    public CurrentAccountServiceImpl(CurrentAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrentAccount create(CurrentAccount account) {
        return repository.save(account);
    }

    @Override
    public CurrentAccount getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compte courant non trouvé avec l'id: " + id));
    }

    @Override
    public List<CurrentAccount> getAll() {
        return repository.findAll();
    }

    @Override
    public CurrentAccount update(Long id, CurrentAccount updatedAccount) {
        CurrentAccount existing = getById(id);
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
