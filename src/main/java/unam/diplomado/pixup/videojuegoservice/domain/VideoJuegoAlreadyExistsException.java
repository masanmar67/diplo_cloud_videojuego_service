package unam.diplomado.pixup.videojuegoservice.domain;

public class VideoJuegoAlreadyExistsException extends RuntimeException{

	public VideoJuegoAlreadyExistsException(String nombre) {
		super("Ya existe un video juego registrado con el nombre " + nombre);
	}
}
