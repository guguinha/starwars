package com.augusto.starwars.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.augusto.starwars.services.exceptions.DataIntegrityException;
import com.augusto.starwars.services.exceptions.Forbidden;
import com.augusto.starwars.services.exceptions.ForbiddenTraidor;
import com.augusto.starwars.services.exceptions.ObjectNotFoundException;
import com.augusto.starwars.services.exceptions.TradeException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(Forbidden.class)
	public ResponseEntity<StandardError> ForbidenDelete(Forbidden e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.FORBIDDEN .value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(TradeException.class)
	public ResponseEntity<StandardError> TradeError(TradeException e, HttpServletRequest request){
		/*
		 * Neste tipo de erro a requisição ocorreu corretamente então retorna 200, 
		 * porem houve erro do dominio do problema, na mensagem vem 400 para indicar o erro
		*/
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.OK).body(err);
	}
	
	@ExceptionHandler(ForbiddenTraidor.class)
	public ResponseEntity<StandardError> TradeError(ForbiddenTraidor e, HttpServletRequest request){
		/*
		 * Neste tipo de erro a requisição ocorreu corretamente então retorna 200, 
		 * porem houve erro do dominio do problema, na mensagem vem 403 pois Traidores são proibidos de qualquer acesso a seus itens
		*/
		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.OK).body(err);
	}
	
}
