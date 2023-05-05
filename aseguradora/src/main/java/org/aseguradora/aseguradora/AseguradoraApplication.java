package org.aseguradora.aseguradora;


import org.aseguradora.aseguradora.bd.ManagerConexion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
;

@SpringBootApplication
public class AseguradoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AseguradoraApplication.class, args);
		ManagerConexion conexion = ManagerConexion.getInstance();
		conexion.start();

		}
	}


