package com.sainsburys.productscrapper.constants;

public class Constants {

    public static final String ALL_PRODUCT_INFO_WRAPPERS = "div.product";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String PRODUCT_DETAILS = "div.productText p";
    public static final int KB_DIVIDER = 1024;

    public static final String TITLE = "div.productInfo a";
    public static final String DETAILS_URL = "div.productInfo a";
    public static final String HREF = "href";
    public static final String PRICE_PER_UNIT = "pricePerUnit";

    public static final String SCRAPE_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";


    private Constants() {

    }
}
