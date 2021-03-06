package com.neytech.mscreditappraiser.entities;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Card {

	private Long id;
	private String name;
	private String flag;
	private BigDecimal basicLimit;
}
