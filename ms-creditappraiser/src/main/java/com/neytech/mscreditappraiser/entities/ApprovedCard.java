package com.neytech.mscreditappraiser.entities;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApprovedCard {

	private String card;
	private String flag;
	private BigDecimal approvedLimit;
}
