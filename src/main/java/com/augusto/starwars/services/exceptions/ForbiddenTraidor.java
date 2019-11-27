package com.augusto.starwars.services.exceptions;

public class ForbiddenTraidor extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ForbiddenTraidor(String msg) {
		super(msg);
	}
	
	public ForbiddenTraidor(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
