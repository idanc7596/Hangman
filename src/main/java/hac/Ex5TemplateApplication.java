package hac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Ex5TemplateApplication class - the main class of the application
 */
@ServletComponentScan
@SpringBootApplication
public class Ex5TemplateApplication {

    /**
     * main method - the main method of the application
     * @param args - the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Ex5TemplateApplication.class, args);
    }

}
