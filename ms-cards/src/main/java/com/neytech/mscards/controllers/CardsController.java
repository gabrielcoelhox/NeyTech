package com.neytech.mscards.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neytech.mscards.dto.CardSaveRequest;
import com.neytech.mscards.entities.Card;
import com.neytech.mscards.services.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardsController {

	private final CardService cardService;
	
	@GetMapping
	public String status() {
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity<Card> cadastro(@RequestBody CardSaveRequest request) {
		Card card = request.toModel();
		cardService.save(card);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(params = "income")
	public ResponseEntity<List<Card>> getMaxIncomeCard(@RequestParam("income") Long income) {
		List<Card> list = cardService.getLessOrEqualCardIncome(income);
		return ResponseEntity.ok(list);
	}
}
