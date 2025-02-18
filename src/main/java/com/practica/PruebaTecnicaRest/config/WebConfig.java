package com.practica.PruebaTecnicaRest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	log.info("REQUEST HTML");
        // Redirige todas las rutas no manejadas al index.html de Angular
        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
    }
}