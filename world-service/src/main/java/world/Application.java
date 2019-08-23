package world;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

@ComponentScan("world")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        run(Application.class, args);
    }
}
