package com.project.first.redMath.customer;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class customerService {
    private final customerRepository customerRepository;

    public  customerService(customerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public customer create(customer Customer){
        Customer.setCreated_at(LocalDateTime.now());
        Customer.setUpdated_at(LocalDateTime.now());
        return customerRepository.save(Customer);
    }

}
