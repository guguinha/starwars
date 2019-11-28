package com.augusto.starwars.services.exceptions;

public class TradeException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		
		public TradeException(String msg) {
			super(msg);
		}
		
		public TradeException(String msg, Throwable cause) {
			super(msg, cause);
		}
}
