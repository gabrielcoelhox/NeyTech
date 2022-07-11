package com.neytech.mscreditappraiser.clients;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPerCustomerResponse {

	private String name;
	private String flag;
	private BigDecimal limiteLiberado;
}
