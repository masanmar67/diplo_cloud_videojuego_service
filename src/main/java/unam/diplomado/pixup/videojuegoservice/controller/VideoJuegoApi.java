package unam.diplomado.pixup.videojuegoservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import unam.diplomado.pixup.videojuegoservice.domain.VideoJuego;

public interface VideoJuegoApi {
	
	@Operation(summary = "Obtener una lista de todos los videojuegos")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", 
		description = "Lista de videojuegos registrados", 
	    content = { 
	    	@Content(mediaType="application/json", 
	    	schema = @Schema(implementation=VideoJuego.class)) }) })	
	@GetMapping
	public Iterable<VideoJuego> obtenerVideoJuegos();

	@Operation(summary = "Actualizar un videojuego por ID")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", 
		description = "Videojuego actualizado", 
	    content = { 
	    	@Content(mediaType="application/json", 
	    	schema = @Schema(implementation=VideoJuego.class)) }),
	  @ApiResponse(responseCode = "404", 
	  	description="No existe videojuego con el ID proporcionado", 
	    content = @Content) })	
	@PutMapping(path = "{id}", consumes = "application/json")
	public ResponseEntity<VideoJuego> actualizarVideoJuego(
			@PathVariable("id")String id, @RequestBody VideoJuego videoJuego);

	
	@Operation(summary = "Registrar Videojuego")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "201", 
		description = "Videojuego Registrado Exitosamente", 
	    content = { 
	    	@Content(mediaType="application/json", 
	    	schema = @Schema(implementation=VideoJuego.class)) }),
	  @ApiResponse(responseCode = "409", 
	  	description="Ya existe un videojuego con el nombre especificado", 
	    content = @Content) })	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public VideoJuego registrarVideoJuego(@NotNull @Valid @RequestBody VideoJuego vj);

	
	@Operation(summary = "Eliminar un videojuego por ID")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "204", 
		description = "Videojuego eliminado", 
	    content = @Content) })	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarVideoJuego(@PathVariable("id") String id);
	
}
