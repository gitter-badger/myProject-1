package com.sainsburys.productscrapper.configuration;

import com.sainsburys.productscrapper.webclient.JsoupWebClient;
import com.sainsburys.productscrapper.webclient.WebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sainsburys.productscrapper.parser.HtmlParserImpl;
import com.sainsburys.productscrapper.parser.Parser;
import com.sainsburys.productscrapper.service.ProductService;
import com.sainsburys.productscrapper.service.ProductServiceImpl;
import com.sainsburys.productscrapper.facade.ResultsFacade;
import com.sainsburys.productscrapper.facade.ResultsFacadeImpl;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    public ResultsFacade resultsFacade() {
        return new ResultsFacadeImpl();
    }

    @Bean
    public Parser parser() {
        return new HtmlParserImpl();
    }

    @Bean
    public WebClient webClient() {
        return new JsoupWebClient();
    }
}
