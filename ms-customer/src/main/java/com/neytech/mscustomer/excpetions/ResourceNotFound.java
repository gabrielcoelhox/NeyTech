package com.neytech.mscustomer.excpetions;

public class ResourceNotFound extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public ResourceNotFound (String msg) {
		super(msg);
	}
}
