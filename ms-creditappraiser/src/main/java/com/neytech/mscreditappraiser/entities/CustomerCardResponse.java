package com.neytech.mscreditappraiser.entities;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerCardResponse {

	private String name;
    private String flag;
    private BigDecimal releasedLimit;
}
