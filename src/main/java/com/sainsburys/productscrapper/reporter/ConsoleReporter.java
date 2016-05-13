package com.sainsburys.productscrapper.reporter;

import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.serializer.ResultSerializer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleReporter implements Reporter {

    private Logger LOG = Logger.getLogger(ConsoleReporter.class);

    @Autowired
    private ResultSerializer resultSerializer;

    @Override
    public void report(Result result) {

        String results = resultSerializer.serialize(result);

        System.out.println(results);
    }

}
