package com.sainsburys.webscrapper.model;

import java.math.BigDecimal;
import java.util.List;

public class Result {

    private final List<Product> results;

    public Result(List<Product> results) {
        this.results = results;
    }

    public List<Product> getResults() {
        return results;
    }

    public BigDecimal getTotal() {

        if (results == null) {
            return null;
        }

        BigDecimal total = results.stream().map(Product::getUnitPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return total;
    }
}
