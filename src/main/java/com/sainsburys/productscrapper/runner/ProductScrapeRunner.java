package com.sainsburys.productscrapper.runner;

import com.sainsburys.productscrapper.facade.ResultsFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class ProductScrapeRunner implements CommandLineRunner {

    private Logger LOG = Logger.getLogger(ProductScrapeRunner.class);

    @Autowired
    private ResultsFacade resultsFacade;

    @Override
    public void run(String... strings) throws Exception {

        String results = resultsFacade.getResults();

        LOG.info(results);
    }
}
