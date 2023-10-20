package co.udea.ssmu.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan({
        "co.udea.ssmu.api.controller",
        "co.udea.ssmu.api.model",
        "co.udea.ssmu.api.services",
        "co.udea.ssmu.api.utils",
})
public class SsmuApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SsmuApiApplication.class, args);
    }

}
