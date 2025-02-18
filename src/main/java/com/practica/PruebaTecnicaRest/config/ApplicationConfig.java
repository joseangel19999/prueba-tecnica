package com.practica.PruebaTecnicaRest.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class ApplicationConfig {
	
	public final String[] DEFAULT_URL = { "/**"};
	public final String[] WHITE_LIST_URL_FRONT_ACCESS= {"http://localhost:4200"};
	public final String AUTHORIZATION="Authorization";
	public final String CONTENT_TYPE="Content-Type";
	public final String[] PETITION_METHOD= {"GET", "POST", "PUT", "DELETE","OPTIONS"}; 

	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(WHITE_LIST_URL_FRONT_ACCESS));
        config.setAllowedHeaders(List.of(AUTHORIZATION,CONTENT_TYPE));
        config.setAllowedMethods(List.of(PETITION_METHOD));

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
