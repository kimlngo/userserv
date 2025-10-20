package com.kimlngo.userserv.controller;

import com.kimlngo.userserv.entity.Customer;
import com.kimlngo.userserv.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allUsers = customerService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createNewCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer, id);
        return new ResponseEntity<>(updatedCustomer, updatedCustomer != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent()
                             .build();
    }
}
