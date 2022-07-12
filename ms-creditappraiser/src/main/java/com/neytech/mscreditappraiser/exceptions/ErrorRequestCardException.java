package com.neytech.mscreditappraiser.exceptions;

public class ErrorRequestCardException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErrorRequestCardException(String message) {
        super(message);
    }
}
