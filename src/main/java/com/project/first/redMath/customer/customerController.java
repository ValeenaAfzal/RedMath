package com.project.first.redMath.customer;

import com.project.first.redMath.account.Account;
import com.project.first.redMath.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class customerController {

    private final customerService CustomerService;

    public customerController(customerService CustomerService) {
        this.CustomerService = CustomerService;
    }

    @PostMapping("/redMath/customer")
    public ResponseEntity<customer> createAccount(@RequestBody customer Customer) {
        customer createdCustomer= CustomerService.create(Customer);
        if (createdCustomer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
