package com.neytech.mscreditappraiser.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCardResponse {

	private String name;
    private String flag;
    private BigDecimal limitReleased;
}
