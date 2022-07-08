package com.neytech.mscustomer.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neytech.mscustomer.controllers.representation.CustomerSaveRequest;
import com.neytech.mscustomer.entities.Customer;
import com.neytech.mscustomer.services.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
	
	private final CustomerService service;
	
	@GetMapping
	public String status() {
		log.info("Obtendo status do microservice de clientes");
		return "ok";
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody CustomerSaveRequest request) {
		Customer model = request.toModel();
		service.save(model);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(model.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
	}
	
	@GetMapping(params = "cpf")
    public ResponseEntity<?> customerData(@RequestParam("cpf") String cpf){
        var model = service.getByCPF(cpf);
        if(model.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(model);
    }
}
