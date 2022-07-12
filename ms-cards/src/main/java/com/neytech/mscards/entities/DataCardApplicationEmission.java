package com.neytech.mscards.entities;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DataCardApplicationEmission {

	private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
