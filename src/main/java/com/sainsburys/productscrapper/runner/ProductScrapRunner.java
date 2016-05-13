package com.sainsburys.productscrapper.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sainsburys.productscrapper.facade.ResultsFacade;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.reporter.Reporter;

@Component
public class ProductScrapRunner implements CommandLineRunner {

    @Autowired
    private ResultsFacade resultsFacade;

    @Autowired
    private Reporter reporter;

    @Override
    public void run(String... strings)  {

        Result results = resultsFacade.getResults();

        reporter.report(results);
    }
}
