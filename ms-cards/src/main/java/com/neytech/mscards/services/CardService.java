package com.neytech.mscards.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neytech.mscards.entities.Card;
import com.neytech.mscards.repository.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

	public final CardRepository cardRepository;
	
	@Transactional
	public Card save(Card card) {
		return cardRepository.save(card);
	}
	
	public List<Card> getLessOrEqualCardIncome(Long income) {
		var rendaBigDecimal = BigDecimal.valueOf(income);
		return cardRepository.findByIncomeLessThanEqual(rendaBigDecimal);
	}
}
