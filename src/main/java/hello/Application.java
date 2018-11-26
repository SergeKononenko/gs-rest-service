// ... makes the project self-launching
// Launch using ./mvnw spring-boot:run
// ... then hit it at http://localhost:8080/greeting?name=Wurlitzer, etc

package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

