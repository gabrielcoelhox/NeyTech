package com.neytech.mscreditappraiser.exceptions;

public class CustomerDataNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public CustomerDataNotFoundException() {
        super("Customer data not found for the informed CPF.");
    }
}
