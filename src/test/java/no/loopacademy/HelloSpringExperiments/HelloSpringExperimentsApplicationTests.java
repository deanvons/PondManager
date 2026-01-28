package no.loopacademy.HelloSpringExperiments;


import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloSpringExperimentsApplicationTests {

	@Test
	void contextLoads() {

		// Arrange
		MockDuckRepository mock = new MockDuckRepository();

		// exptedk
		DuckService duckeService  = new DuckService(mock);

		duckeService.listDucks();
	}




}
