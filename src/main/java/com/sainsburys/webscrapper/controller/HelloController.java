package com.sainsburys.webscrapper.controller;

import com.sainsburys.webscrapper.model.Word;
import com.sainsburys.webscrapper.service.WordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController implements CommandLineRunner {

    @Autowired
    private WordService wordService;

    private Logger LOGGER = Logger.getLogger(HelloController.class);

    @Override
    public void run(String... strings) throws Exception {

        Word word = wordService.createWord("Scrapper Starts Here");

        LOGGER.info("*************************************");
        LOGGER.info(word.getText());
        LOGGER.info("*************************************");
    }
}
