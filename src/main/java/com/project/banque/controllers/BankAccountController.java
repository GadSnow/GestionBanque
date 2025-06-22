package com.project.banque.controllers;

import com.project.banque.entities.AccountOperation;
import com.project.banque.entities.CurrentAccount;
import com.project.banque.services.interf.AccountOperationService;
import com.project.banque.services.interf.CurrentAccountService;
import com.project.banque.services.interf.CustomerService;
import com.project.banque.utils.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/bank-account")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BankAccountController {

    private final CurrentAccountService service;
    private final AccountOperationService operationService;
    private final CustomerService customerService;


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
