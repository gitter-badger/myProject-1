package com.sainsburys.webscrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sainsburys.webscrapper.Parser.HtmlParser;
import com.sainsburys.webscrapper.Parser.Parser;
import com.sainsburys.webscrapper.Repository.ProductRepository;
import com.sainsburys.webscrapper.Repository.ProductRepositoryImpl;
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
