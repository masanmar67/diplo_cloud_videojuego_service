package unam.diplomado.pixup.videojuegoservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import unam.diplomado.pixup.videojuegoservice.domain.VideoJuego;
import unam.diplomado.pixup.videojuegoservice.service.VideoJuegoService;

@RestController
@RequestMapping(path = "api/videojuego", produces = "application/json")
@CrossOrigin(origins = "*")
public class VideoJuegoController implements VideoJuegoApi {
	
	@Autowired
	private VideoJuegoService videoJuegoService;
	
	@Override
	public Iterable<VideoJuego> obtenerVideoJuegos(){
		return videoJuegoService.obtenerTodos();
	}
	
	@Override
	public ResponseEntity<VideoJuego> actualizarVideoJuego(String id, VideoJuego videoJuego){
		VideoJuego juegoActualizado = videoJuegoService.actualizarNombreVideoJuego(id, videoJuego);
		if (juegoActualizado != null) {
			return new ResponseEntity<VideoJuego>(juegoActualizado, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public VideoJuego registrarVideoJuego(VideoJuego vj) {
		return videoJuegoService.registrarVideoJuego(vj);
	}
	
	@Override
	public ResponseEntity eliminarVideoJuego(String id) {
		Optional<VideoJuego> vj = videoJuegoService.findById(id);
		if(vj.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		videoJuegoService.eliminarVideoJuego(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
