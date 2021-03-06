package com.neytech.mscreditappraiser.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;	
	private Integer age;
}
