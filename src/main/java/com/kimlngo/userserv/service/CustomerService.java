package com.kimlngo.userserv.service;

import com.kimlngo.userserv.assembler.CustomerModelAssembler;
import com.kimlngo.userserv.entity.Customer;
import com.kimlngo.userserv.exception.CustomerNotFoundException;
import com.kimlngo.userserv.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerModelAssembler customerModelAssembler;

    @Autowired
    public CustomerService(CustomerRepository userRepository, CustomerModelAssembler customerModelAssembler) {
        this.customerRepository = userRepository;
        this.customerModelAssembler = customerModelAssembler;
    }

    public List<EntityModel<Customer>> getAllCustomers() {
        return customerRepository.findAll()
                                 .stream()
                                 .map(customerModelAssembler::toModel)
                                 .toList();
    }

    public EntityModel<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id)
                                 .map(customerModelAssembler::toModel)
                                 .orElseThrow(() -> new CustomerNotFoundException(id));
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
