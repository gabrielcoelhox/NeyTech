package com.neytech.mscreditappraiser.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neytech.mscreditappraiser.entities.CustomerData;

@FeignClient(value = "ms-customer", path = "/customers")
public interface CustomerResourceClient {

	@GetMapping(params = "cpf")
	public ResponseEntity<CustomerData> customerData(@RequestParam("cpf")String cpf) ;
}
