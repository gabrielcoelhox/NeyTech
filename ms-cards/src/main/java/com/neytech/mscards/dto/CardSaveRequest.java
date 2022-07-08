package com.neytech.mscards.dto;

import java.math.BigDecimal;

import com.neytech.mscards.entities.Card;
import com.neytech.mscards.entities.enums.CardFlag;

import lombok.Data;

@Data
public class CardSaveRequest {

	private String name;
	private CardFlag flag;
	private BigDecimal income;
	private BigDecimal limit;
	
	public Card toModel() {
		return new Card(name, flag, income, limit);
	}
}
