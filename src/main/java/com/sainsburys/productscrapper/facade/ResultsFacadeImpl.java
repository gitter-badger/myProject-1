package com.sainsburys.productscrapper.facade;

import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultsFacadeImpl implements ResultsFacade {

    @Autowired
    private ProductService productService;

    @Override
    public Result getResults() {

        List<Product> products = productService.getAll();
        Result result = new Result(products);

        return result;
    }
}
