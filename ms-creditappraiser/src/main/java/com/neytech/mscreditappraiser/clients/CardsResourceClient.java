package com.neytech.mscreditappraiser.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neytech.mscreditappraiser.entities.Card;
import com.neytech.mscreditappraiser.entities.CustomerCardResponse;

@FeignClient(value = "ms-cards", path = "/cards")
public interface CardsResourceClient {

	@GetMapping(params = "cpf")
	ResponseEntity<List<CustomerCardResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf);
	
	@GetMapping(params = "income")
	ResponseEntity<List<Card>> getMaxIncomeCard(@RequestParam("income") Long income);
}
