package com.project.banque.controllers;

import com.project.banque.entities.AccountOperation;
import com.project.banque.entities.SavingAccount;
import com.project.banque.services.interf.AccountOperationService;
import com.project.banque.services.interf.SavingAccountService;
import com.project.banque.utils.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/saving-account")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SavingAccountController {

    private final SavingAccountService service;
    private final AccountOperationService operationService;

    @PostMapping
    public ApiResponse<Object> create(@RequestBody SavingAccount account) {
        try {
            AccountOperation operation = new AccountOperation();
            operation.setAmount(account.getBalance());
            operation.setType(account.getType());
            operation.setReference(UUID.randomUUID().toString());
            operation.setBankAccount(account); // ou setSavingAccount(account) selon ta structure

            operationService.createAccountOperation(operation);

            SavingAccount created = service.create(account);
            return ApiResponse.success(created, "Compte épargne créé avec succès");
        } catch (Exception e) {
            return ApiResponse.error(400, "Erreur lors de la création : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id) {
        try {
            SavingAccount account = service.getById(id);
            return ApiResponse.success(account, "Compte épargne récupéré avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<Object> getAll() {
        try {
            List<SavingAccount> accounts = service.getAll();
            return ApiResponse.success(accounts, "Liste des comptes épargne récupérée avec succès");
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody SavingAccount account) {
        try {
            service.getById(id);
            SavingAccount updated = service.update(id, account);
            return ApiResponse.success(updated, "Compte épargne mis à jour avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        try {
            service.getById(id);

            service.delete(id);
            return ApiResponse.success(null, "Compte épargne supprimé avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }
}
