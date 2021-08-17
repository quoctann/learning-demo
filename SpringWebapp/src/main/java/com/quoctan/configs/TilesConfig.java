package com.quoctan.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;


@Configuration
public class TilesConfig {
    @Bean
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        // Của thư viện cung cấp
        resolver.setViewClass(TilesView.class);
        // Hthống cho phép nhiều view resolver, số càng nhỏ độ ưu tiên càng cao
        resolver.setOrder(-2);
        return resolver;
    }
    
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer c = new TilesConfigurer();
        c.setDefinitions("/WEB-INF/tiles.xml");
        c.setCheckRefresh(true);
        return c;
    }
}
