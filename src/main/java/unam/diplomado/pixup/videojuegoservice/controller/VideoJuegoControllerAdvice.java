package unam.diplomado.pixup.videojuegoservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import unam.diplomado.pixup.videojuegoservice.domain.VideoJuegoAlreadyExistsException;


@RestControllerAdvice
public class VideoJuegoControllerAdvice {
	@ExceptionHandler(VideoJuegoAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	private String videoJuegoAlreadyExistsExceptionHandler(
			VideoJuegoAlreadyExistsException exception) {
		return exception.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private String validatorHandler(
			MethodArgumentNotValidException exception) {
		return "Existen errores de validación en el payload recibido";
	}


}
