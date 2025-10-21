package com.kimlngo.userserv.assembler;

import com.kimlngo.userserv.controller.CustomerController;
import com.kimlngo.userserv.entity.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
    private static final String CUSTOMERS = "customers";

    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).getCustomerById(customer.getUserId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel(CUSTOMERS));
    }
}
