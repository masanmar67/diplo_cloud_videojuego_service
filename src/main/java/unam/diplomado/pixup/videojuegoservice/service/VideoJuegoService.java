package unam.diplomado.pixup.videojuegoservice.service;

import java.util.Optional;

import unam.diplomado.pixup.videojuegoservice.domain.VideoJuego;

public interface VideoJuegoService {
	
	VideoJuego actualizarNombreVideoJuego(String id, VideoJuego vj);
	Iterable<VideoJuego> obtenerTodos();
	VideoJuego registrarVideoJuego(VideoJuego vj);
	void eliminarVideoJuego(String id);
	Optional<VideoJuego> findById(String id);

}
