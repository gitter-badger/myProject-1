package com.sainsburys.productscrapper.reporter;

import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.serializer.ResultSerializer;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleReporter implements Reporter {

    @Autowired
    private ResultSerializer resultSerializer;

    @Override
    public void report(Result result) {

        String results = resultSerializer.serialize(result);

        System.out.println(results);
    }

}
