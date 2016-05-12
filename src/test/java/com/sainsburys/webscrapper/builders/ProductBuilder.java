package com.sainsburys.webscrapper.builders;

import com.sainsburys.webscrapper.model.Product;

public class ProductBuilder {

    private String title;
    private String detailsUrl;
    private String pricePerUnit;
    private double pageSize;
    private String description;

    public ProductBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder withDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
        return this;
    }

    public ProductBuilder withPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public ProductBuilder withPageSize(double pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Product build() {
        Product product = new Product(this.title, this.detailsUrl, this.pricePerUnit, this.pageSize, this.description);

        return product;

    }
}
