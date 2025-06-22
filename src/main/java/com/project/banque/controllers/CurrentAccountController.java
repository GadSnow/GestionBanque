package com.project.banque.controllers;

import com.project.banque.entities.AccountOperation;
import com.project.banque.entities.CurrentAccount;
import com.project.banque.services.interf.AccountOperationService;
import com.project.banque.services.interf.CurrentAccountService;
import com.project.banque.utils.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/current-account")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CurrentAccountController {

    private final CurrentAccountService service;
    private final AccountOperationService operationService;

    @PostMapping
    public ApiResponse<Object> create(@RequestBody CurrentAccount account) {
        try {
            // Création de l'opération initiale liée au solde
            AccountOperation operation = new AccountOperation();
            operation.setAmount(account.getBalance());
            operation.setType(account.getType());
            operation.setReference(UUID.randomUUID().toString());
            operation.setBankAccount(account);  // ou .setCurrentAccount(account) si tu préfères
            operationService.createAccountOperation(operation);

            CurrentAccount created = service.create(account);
            return ApiResponse.success(created, "Compte courant créé avec succès");
        } catch (Exception e) {
            return ApiResponse.error(400, "Erreur lors de la création : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id) {
        try {
            CurrentAccount account = service.getById(id);
            return ApiResponse.success(account, "Compte courant récupéré avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<Object> getAll() {
        try {
            List<CurrentAccount> accounts = service.getAll();
            return ApiResponse.success(accounts, "Liste des comptes courants récupérée avec succès");
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody CurrentAccount account) {
        try {
            service.getById(id);
            CurrentAccount updated = service.update(id, account);
            return ApiResponse.success(updated, "Compte courant mis à jour avec succès");
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
            return ApiResponse.success(null, "Compte courant supprimé avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }
}
