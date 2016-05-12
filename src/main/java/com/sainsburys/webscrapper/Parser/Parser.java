package com.sainsburys.webscrapper.Parser;

import org.jsoup.select.Elements;

public interface Parser {

    Elements getAllProducts();

    double getPageSize(String detailsUrl);

    String getProductDescription(String detailsUrl);
}
