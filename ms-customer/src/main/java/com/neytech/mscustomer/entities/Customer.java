package com.neytech.mscustomer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String cpf;
	private Integer age;
	
	public Customer(String name, String cpf, Integer age) {
		this.name = name;
		this.cpf = cpf;
		this.age = age;
	}
}
