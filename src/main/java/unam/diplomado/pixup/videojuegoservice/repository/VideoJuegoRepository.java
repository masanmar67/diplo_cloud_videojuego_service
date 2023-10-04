package unam.diplomado.pixup.videojuegoservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import unam.diplomado.pixup.videojuegoservice.domain.VideoJuego;

public interface VideoJuegoRepository 
	extends MongoRepository<VideoJuego, String>{
	
	Optional<VideoJuego> findByNombre(String nombre);

}
