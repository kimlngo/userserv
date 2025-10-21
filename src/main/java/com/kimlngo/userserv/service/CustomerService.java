package com.kimlngo.userserv.service;

import com.kimlngo.userserv.entity.Customer;
import com.kimlngo.userserv.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository userRepository) {
        this.customerRepository = userRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                                 .orElse(null);
    }

    public Customer createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer, Long custId) {
        Optional<Customer> custOpt = customerRepository.findById(custId);

        if (custOpt.isPresent()) {
            Customer existingCustomer = custOpt.get();
            if (customer.getName() != null) {
                existingCustomer.setName(customer.getName());
            }

            if (customer.getAddress() != null) {
                existingCustomer.setAddress(customer.getAddress());
            }

            if (customer.getPhoneNumber() != null) {
                existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            }

            return customerRepository.save(existingCustomer);
        } else
            return null;
    }

    public void deleteCustomer(Long custId) {
        customerRepository.deleteById(custId);
    }
}
