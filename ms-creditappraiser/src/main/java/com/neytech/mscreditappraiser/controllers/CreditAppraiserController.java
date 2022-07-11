package com.neytech.mscreditappraiser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neytech.mscreditappraiser.entities.CustomerSituation;
import com.neytech.mscreditappraiser.exceptions.CustomerDataNotFoundException;
import com.neytech.mscreditappraiser.exceptions.ErroComunicacaoMicroservicesException;
import com.neytech.mscreditappraiser.services.CreditAppraiserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

	private final CreditAppraiserService creditAppraiserService;
	
	@GetMapping
    public String status(){
        return "ok";
    }
	
	@GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity<?> customerSituationConsult(@RequestParam("cpf") String cpf) {
        try {
            CustomerSituation customerSituation = creditAppraiserService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
