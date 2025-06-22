package com.project.banque.services.impl;

import com.project.banque.entities.Customer;
import com.project.banque.repositories.CustomerRepository;
import com.project.banque.services.interf.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec l'id: " + id));
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer update(Long id, Customer updatedCustomer) {
        Customer existing = getById(id);
        existing.setNom(updatedCustomer.getNom());
        existing.setPrenom(updatedCustomer.getPrenom());
        existing.setBtEnabled(updatedCustomer.getBtEnabled());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
