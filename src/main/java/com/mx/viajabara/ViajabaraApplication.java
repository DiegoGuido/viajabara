package com.mx.viajabara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ViajabaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViajabaraApplication.class, args);
	}

}
