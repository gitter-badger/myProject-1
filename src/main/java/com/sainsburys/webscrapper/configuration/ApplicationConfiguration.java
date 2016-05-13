package com.sainsburys.webscrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sainsburys.webscrapper.parser.HtmlParser;
import com.sainsburys.webscrapper.parser.Parser;
import com.sainsburys.webscrapper.repository.ProductRepository;
import com.sainsburys.webscrapper.repository.ProductRepositoryImpl;
import com.sainsburys.webscrapper.facade.ResultsFacade;
import com.sainsburys.webscrapper.facade.ResultsFacadeImpl;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public ResultsFacade resultsFacade() {
        return new ResultsFacadeImpl();
    }

    @Bean
    public Parser parser() {
        return new HtmlParser();
    }
}
