package com.project.banque.controllers;

import com.project.banque.entities.Customer;
import com.project.banque.services.interf.CustomerService;
import com.project.banque.utils.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<Object> create(@RequestBody Customer customer) {
        try {
            Customer created = customerService.create(customer);
            return ApiResponse.success(created, "Client créé avec succès");
        } catch (Exception e) {
            return ApiResponse.error(400, "Erreur lors de la création : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getById(id);
            return ApiResponse.success(customer, "Client récupéré avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<Object> getAll() {
        try {
            List<Customer> customers = customerService.getAll();
            return ApiResponse.success(customers, "Liste des clients récupérée avec succès");
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        try {
            customerService.getById(id);

            Customer updated = customerService.update(id, updatedCustomer);
            return ApiResponse.success(updated, "Client mis à jour avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        try {
            customerService.getById(id);

            customerService.delete(id);
            return ApiResponse.success(null, "Client supprimé avec succès");
        } catch (EntityNotFoundException e) {
            return ApiResponse.error(404, e.getMessage());
        } catch (Exception e) {
            return ApiResponse.error(500, "Erreur interne : " + e.getMessage());
        }
    }

}
