package com.sainsburys.webscrapper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Product {

    private static final String NON_NUMERIC_REGEX = "[^\\d.]";

    private final String title;

    @JsonIgnore
    private final String detailsUrl;
    private final double pageSize;

    @JsonProperty("unit_price")
    private final BigDecimal unitPrice;

    private final String description;

    public Product(String title, String detailsUrl, String pricePerUnit, double pageSize, String description) {
        this.title = title;
        this.detailsUrl = detailsUrl;
        this.unitPrice = new BigDecimal(pricePerUnit.replaceAll(NON_NUMERIC_REGEX, ""));
        this.pageSize = pageSize;
        this.description = description;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getSize() {
        return new DecimalFormat("#.#").format(pageSize) + "Kb";
    }

    public String getDescription() {
        return description;
    }
}
