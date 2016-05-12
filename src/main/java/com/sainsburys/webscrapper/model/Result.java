package com.sainsburys.webscrapper.model;

import java.math.BigDecimal;
import java.util.List;

public class Result {

    private final List<Product> results;
    private final BigDecimal total;

    public Result(List<Product> results) {
        this.results = results;
        this.total = results.stream().map(Product::getUnitPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Product> getResults() {
        return results;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
