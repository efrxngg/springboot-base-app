package com.base.app.dto;

import com.base.app.entity.Customer;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
public record CreateCustomerDto(String name) implements Serializable {
}