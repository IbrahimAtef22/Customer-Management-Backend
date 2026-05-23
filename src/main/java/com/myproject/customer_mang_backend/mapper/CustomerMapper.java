package com.myproject.customer_mang_backend.mapper;

import com.myproject.customer_mang_backend.dto.CustomerDto;
import com.myproject.customer_mang_backend.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper{
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto dto);

    List<CustomerDto> toDtoList(List<Customer> customerList);

    void updateEntityFromDto(CustomerDto customerDto, @MappingTarget Customer existing);
}
