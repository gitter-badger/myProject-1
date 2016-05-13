package com.sainsburys.productscrapper.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class ResultsFacadeImpl implements ResultsFacade {

    private Logger LOG = Logger.getLogger(ResultsFacadeImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public String getResults() {

        List<Product> products = productService.getAll();
        Result result = new Result(products);

        String resultAsJson = getJson(result);

        return resultAsJson;
    }

    private String getJson(Result result) {

        String resultAsJson = EMPTY;

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            resultAsJson = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            LOG.error("error converting results to json", e);
        }

        return resultAsJson;
    }
}
