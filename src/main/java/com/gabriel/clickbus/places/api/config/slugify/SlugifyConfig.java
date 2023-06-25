package com.gabriel.clickbus.places.api.config.slugify;

import com.github.slugify.Slugify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlugifyConfig {

    @Bean
    public Slugify slugify() {
        return Slugify.builder().build();
    }
}
