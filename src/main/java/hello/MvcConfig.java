package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/example").setViewName("example");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/signin").setViewName("signin");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/contact").setViewName("contact");


    }
}
