package com.sainsburys.productscrapper.parser;

import com.sainsburys.productscrapper.webclient.WebClient;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class HtmlParserImpl implements Parser {

    public static final String ALL_PRODUCT_INFO_WRAPPERS = "div.product";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String PRODUCT_DETAILS = "div.productText p";
    private static final int KB_DIVIDER = 1024;

    @Value("${scrapper.url}")
    private String url;

    @Autowired
    private WebClient webClient;

    @Override
    public Elements getAllProducts() {

        Document document = webClient.getDocument(url);
        Elements elements = document.select(ALL_PRODUCT_INFO_WRAPPERS);

        return elements;
    }

    @Override
    public double getPageSize(String detailsUrl) {

        Connection.Response response = webClient.executeRequest(detailsUrl);
        String contentLength = response.header(CONTENT_LENGTH);
        double pageSize = contentLength == null ? Double.valueOf("0.0") : (Double.valueOf(contentLength) / KB_DIVIDER);

        return pageSize;
    }

    @Override
    public String getProductDescription(String detailsUrl) {

        Document document = webClient.getDocument(detailsUrl);
        Elements elements = document.select(PRODUCT_DETAILS);
        Element first = elements.first();

        String description = (first == null) ? EMPTY : first.text();

        return description;
    }
}
