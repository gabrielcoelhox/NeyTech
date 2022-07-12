package com.neytech.mscreditappraiser.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSituation {

	private CustomerData customer;
	private List<CustomerCardResponse> cards;
}
