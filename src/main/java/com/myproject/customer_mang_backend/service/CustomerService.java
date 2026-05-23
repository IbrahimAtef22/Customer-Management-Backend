package com.myproject.customer_mang_backend.service;

import com.myproject.customer_mang_backend.dto.CustomerDto;
import com.myproject.customer_mang_backend.exception.EntityAlreadyExistsException;
import com.myproject.customer_mang_backend.exception.EntityNotFoundException;
import com.myproject.customer_mang_backend.mapper.CustomerMapper;
import com.myproject.customer_mang_backend.model.Customer;
import com.myproject.customer_mang_backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with this id doesn't exist"));
    }

    public Customer createCustomer(CustomerDto customerDto) {
        Optional<Customer> existCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if (existCustomer.isPresent()) {
            throw new EntityAlreadyExistsException("This customer already exists!!");
        }
        return customerRepository.save(customerMapper.toEntity(customerDto));
    }

    public Customer updateCustomer(CustomerDto customerDto) {
        Customer existing = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("This Customer id is not found"));
        customerDto.setCreatedAt(existing.getCreatedAt());
        customerMapper.updateEntityFromDto(customerDto, existing);
        return customerRepository.save(existing);
    }

    public void deleteCustomer(int id) {
        Customer existCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with this id doesn't exist"));
        customerRepository.delete(existCustomer);
    }

}
