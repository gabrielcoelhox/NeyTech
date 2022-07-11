package com.neytech.mscustomer.controllers.representation;

import com.neytech.mscustomer.entities.Customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerSaveRequest {
	
	private String cpf;
	private String name;
	private Integer age;
	
	public Customer toModel() {
		return new Customer(name, cpf, age);
	}
}
