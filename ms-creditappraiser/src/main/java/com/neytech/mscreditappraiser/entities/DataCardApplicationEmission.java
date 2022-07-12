package com.neytech.mscreditappraiser.entities;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DataCardApplicationEmission {

	private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
