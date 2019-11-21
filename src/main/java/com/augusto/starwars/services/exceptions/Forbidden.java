package com.augusto.starwars.services.exceptions;

public class Forbidden extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public Forbidden(String msg) {
		super(msg);
	}
	
	public Forbidden(String msg, Throwable cause) {
		super(msg, cause);
	}
}
