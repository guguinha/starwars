package com.augusto.starwars.services.exceptions;

public class Forbiden extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public Forbiden(String msg) {
		super(msg);
	}
	
	public Forbiden(String msg, Throwable cause) {
		super(msg, cause);
	}
}
