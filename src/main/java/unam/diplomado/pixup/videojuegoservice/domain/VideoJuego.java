package unam.diplomado.pixup.videojuegoservice.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
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
