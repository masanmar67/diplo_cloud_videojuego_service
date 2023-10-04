package unam.diplomado.pixup.videojuegoservice.domain;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "videojuego")
public class VideoJuego {
	
	@Id
	private String id;
	@NotBlank(message = "El nombre no puede estar en blanco")
	private String nombre;
	private String genero;
	private String clasificación;

}
