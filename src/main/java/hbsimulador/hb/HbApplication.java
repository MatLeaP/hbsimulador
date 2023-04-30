package hbsimulador.hb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbApplication.class, args);
		System.out.println("App Ok!!!!");
	}

}
