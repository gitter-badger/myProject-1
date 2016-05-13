package com.sainsburys.productscrapper.parser;

import org.jsoup.select.Elements;

public interface Parser {

    Elements getAllProducts();

    double getPageSize(String detailsUrl);

    String getProductDescription(String detailsUrl);
}
