package com.myproject.customer_mang_backend.controller;

import com.myproject.customer_mang_backend.dto.CustomerDto;
import com.myproject.customer_mang_backend.mapper.CustomerMapper;
import com.myproject.customer_mang_backend.model.Customer;
import com.myproject.customer_mang_backend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerMapper.toDtoList(customerList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody
                                                          CustomerDto customerDto) {
        customerDto.setId(0);
        log.info("Creating a new customer with name ({})", customerDto.getName());
        Customer customer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id,
                                                      @Valid @RequestBody
                                                      CustomerDto customerDto) {
        customerDto.setId(id);
        log.info("Updating a customer with id ({})", customerDto.getId());
        Customer updatedCustomer = customerService.updateCustomer(customerDto);
        return new ResponseEntity<>(customerMapper.toDto(updatedCustomer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
