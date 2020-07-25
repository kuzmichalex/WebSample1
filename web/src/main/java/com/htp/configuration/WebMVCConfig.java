//Security
/*Добавлен был для того, чтобы филбтр не набрасывался на /swagger */

package com.htp.configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").
				addResourceLocations("classpath:/META-INF/resources");
	}
}
