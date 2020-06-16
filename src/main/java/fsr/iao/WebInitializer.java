package fsr.iao;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {
  @Override
protected SpringApplicationBuilder configure(
		SpringApplicationBuilder builder) {
	// TODO Auto-generated method stub
	return  builder.sources(MyProduitsApplication.class);
}

}
