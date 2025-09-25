package accountmgmt;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AccountManagementApplication {
	
	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AccountManagementApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8088"));
        app.run(args);
    }

}
