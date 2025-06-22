package com.project.banque.services.interf;

import com.project.banque.entities.Customer;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);
    Customer getById(Long id);
    List<Customer> getAll();
    Customer update(Long id, Customer updatedCustomer);
    void delete(Long id);
}
