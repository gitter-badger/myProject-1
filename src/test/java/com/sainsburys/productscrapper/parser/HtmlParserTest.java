package com.sainsburys.productscrapper.parser;

import org.jsoup.select.Elements;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

public class HtmlParserTest {

    @InjectMocks
    private Parser htmlParser = new HtmlParserImpl();

    @Test
    public void should_() {

        // Given

        // When
        Elements allProducts = htmlParser.getAllProducts();

        // Then
        assertThat(allProducts).isNull();
    }

}