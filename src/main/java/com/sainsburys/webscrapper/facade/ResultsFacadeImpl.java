package com.sainsburys.webscrapper.facade;

import com.sainsburys.webscrapper.repository.ProductRepository;
import com.sainsburys.webscrapper.model.Product;
import com.sainsburys.webscrapper.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultsFacadeImpl implements ResultsFacade {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Result getResults() {

        List<Product> products = productRepository.getAll();

        Result result = new Result(products);

        return result;
    }
}
