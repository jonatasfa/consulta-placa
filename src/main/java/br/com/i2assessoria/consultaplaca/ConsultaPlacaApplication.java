package br.com.i2assessoria.consultaplaca;

import br.com.i2assessoria.consultaplaca.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ConsultaPlacaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaPlacaApplication.class, args);
	}
}
