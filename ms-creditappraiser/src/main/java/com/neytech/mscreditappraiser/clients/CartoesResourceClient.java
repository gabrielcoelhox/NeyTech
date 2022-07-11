package com.neytech.mscreditappraiser.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neytech.mscreditappraiser.entities.Card;

@FeignClient(value = "ms-cards", path = "/cards")
public interface CartoesResourceClient {

	@GetMapping(params = "cpf")
	ResponseEntity<List<CardsPerCustomerResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf);
	
	@GetMapping(params = "income")
	public ResponseEntity<List<Card>> getCartoesRendaAteh(@RequestParam("income") Long income);
}
