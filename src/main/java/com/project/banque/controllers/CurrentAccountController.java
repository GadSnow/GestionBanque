package com.project.banque.controllers;

import com.project.banque.entities.AccountOperation;
import com.project.banque.entities.CurrentAccount;
import com.project.banque.services.interf.AccountOperationService;
import com.project.banque.services.interf.CurrentAccountService;
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

    final CurrentAccountService service;

    final AccountOperationService operationService;

    @PostMapping
    public ResponseEntity<CurrentAccount> create(@RequestBody CurrentAccount account) {

        AccountOperation operation = new AccountOperation();
        operation.setAmount(account.getBalance());
        operation.setType(account.getType());
        operation.setReference(UUID.randomUUID().toString());

        operationService.createAccountOperation(operation);

        return ResponseEntity.ok(service.create(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrentAccount> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CurrentAccount>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrentAccount> update(@PathVariable Long id, @RequestBody CurrentAccount account) {
        return ResponseEntity.ok(service.update(id, account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
