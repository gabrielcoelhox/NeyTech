package com.neytech.mscreditappraiser.entities;

import java.math.BigDecimal;

import com.neytech.mscreditappraiser.entities.enums.CardFlag;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Card {

	private Long id;
	private String name;
	private CardFlag flag;
	private BigDecimal basicLimit;
}
