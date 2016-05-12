package com.sainsburys.webscrapper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sainsburys.webscrapper.facade.ResultsFacade;
import com.sainsburys.webscrapper.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class ProductScrapper implements CommandLineRunner {

    private Logger LOGGER = Logger.getLogger(ProductScrapper.class);

    @Autowired
    private ResultsFacade resultsFacade;

    @Override
    public void run(String... strings) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Result result = resultsFacade.getResults();
        String resultAsJson = mapper.writeValueAsString(result);

        LOGGER.info(resultAsJson);
    }
}
