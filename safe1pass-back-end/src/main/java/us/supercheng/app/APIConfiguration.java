package us.supercheng.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by cl799honchen on 7/23/2017.
 */
@SpringBootApplication
public class APIConfiguration extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APIConfiguration.class);
    }

    public static void main (String[]  args) {
        SpringApplication.run(APIConfiguration.class, args);
    }
}
