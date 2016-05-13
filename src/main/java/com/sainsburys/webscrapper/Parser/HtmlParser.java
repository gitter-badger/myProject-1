package com.sainsburys.webscrapper.parser;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class HtmlParser implements Parser {

    private Logger LOG = Logger.getLogger(HtmlParser.class);

    private static final String ALL_PRODUCT_INFO_WRAPPERS = "div.product";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final int KB_DIVIDER = 1024;
    private static final String PRODUCT_DETAILS = "div.productText p";

    @Value("${scrapper.url}")
    private String url;

    @Override
    public Elements getAllProducts() {

        Elements elements = null;

        try {

            Document doc = Jsoup.connect(url).get();

            elements = doc.select(ALL_PRODUCT_INFO_WRAPPERS);

        } catch (IOException e) {
            LOG.error("Error connecting to url", e);
        }

        return elements;
    }

    @Override
    public double getPageSize(String detailsUrl) {

        double pageSize = 0.0;

        try {
            Connection.Response response = Jsoup.connect(detailsUrl).execute();

            String contentLength = response.header(CONTENT_LENGTH);

            pageSize = contentLength == null ? Double.valueOf("0.0") : (Double.valueOf(contentLength) / KB_DIVIDER);

        } catch (IOException e) {
            LOG.error("Error connecting to details url", e);
        }

        return pageSize;
    }

    @Override
    public String getProductDescription(String detailsUrl) {

        String description = null;

        try {

            Document doc = Jsoup.connect(detailsUrl).get();

            Element first = doc.select(PRODUCT_DETAILS).first();

            description = (first == null) ? StringUtils.EMPTY : first.text();

        } catch (IOException e) {
            LOG.error("Error connecting to url", e);
        }

        return description;
    }

}
