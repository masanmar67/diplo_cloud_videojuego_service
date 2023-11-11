package unam.diplomado.pixup.videojuegoservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideojuegoServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void getNotificacionesTest() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<NotificacionResponse[]> response = testRestTemplate.getForEntity(BASE_URL, NotificacionResponse[].class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	
}
