package com.dos.protection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class DosProtectionSimulationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =SpringApplication.run(DosProtectionSimulationApplication.class, args);
		try  {
			int i =System.in.read();
			exitApplication(ctx);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exitApplication(ConfigurableApplicationContext context) {
		int exitCode = SpringApplication.exit(context,() -> 0);

		System.exit(exitCode);
	}

}
