package unam.diplomado.pixup.videojuegoservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unam.diplomado.pixup.videojuegoservice.domain.VideoJuego;
import unam.diplomado.pixup.videojuegoservice.domain.VideoJuegoAlreadyExistsException;
import unam.diplomado.pixup.videojuegoservice.repository.VideoJuegoRepository;

@Service
public class VideoJuegoServiceImpl implements VideoJuegoService{
	
	@Autowired
	private VideoJuegoRepository videoJuegoRepository;

	@Override
	public VideoJuego actualizarNombreVideoJuego(String id, VideoJuego vj) {
		Optional<VideoJuego> juegoExistente = videoJuegoRepository.findById(id);
		if(juegoExistente.isPresent()) {
			VideoJuego juegoActualizar = juegoExistente.get();
			juegoActualizar.setNombre(vj.getNombre());
			videoJuegoRepository.save(juegoActualizar);
			return juegoActualizar;
		}
		return null;
	}

	@Override
	public Iterable<VideoJuego> obtenerTodos() {
		return videoJuegoRepository.findAll();
	}

	@Override
	public VideoJuego registrarVideoJuego(VideoJuego vj) {
		Optional<VideoJuego> vjExistente = videoJuegoRepository.findByNombre(vj.getNombre());
		if(vjExistente.isPresent()) {
			throw new VideoJuegoAlreadyExistsException(vj.getNombre());
		}
		return videoJuegoRepository.save(vj);
	}

	@Override
	public void eliminarVideoJuego(String id) {
		videoJuegoRepository.deleteById(id);
	}

	@Override
	public Optional<VideoJuego> findById(String id) {
		return videoJuegoRepository.findById(id);
	}

}
