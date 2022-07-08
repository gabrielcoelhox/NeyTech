package com.neytech.mscustomer.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neytech.mscustomer.entities.Customer;
import com.neytech.mscustomer.infra.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	
	private final CustomerRepository repository;
	
	@Transactional
	public Customer save(Customer customer) {
		return repository.save(customer);
	}
	
	public Optional<Customer> getByCPF(String cpf) {
		return repository.findByCpf(cpf);
	}
}
