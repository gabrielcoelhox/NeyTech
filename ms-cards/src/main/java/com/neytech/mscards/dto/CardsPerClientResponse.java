package com.neytech.mscards.dto;

import java.math.BigDecimal;

import com.neytech.mscards.entities.ClientCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPerClientResponse {

	private String name;
	private String flag;
	private BigDecimal limitReleased;
	
	public static CardsPerClientResponse fromModel(ClientCard model) {
		return new CardsPerClientResponse(
			model.getCard().getName(),
			model.getCard().getFlag().toString(),
			model.getLimit());
	}
}
