package com.sainsburys.webscrapper.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void should_return_formatted_page_size() throws Exception {

        // Given
        String title = "Sainsbury's Kiwi Fruit, Ripe & Ready x4";
        String description = "Kiwi";
        String pricePerUnit = "12.20";
        double pageSize = 38.6;
        String detailsUrl = "http://www.detailsurl.cm/some.html";

        Product product = new Product(title, detailsUrl, pricePerUnit, pageSize, description);

        // When
        String actualSize = product.getSize();

        // Then
        assertThat(actualSize).isEqualTo("38.6Kb");

    }

    @Test
    public void should_formatted_page_size_to_one_decimal_point() throws Exception {

        // Given
        String title = "Sainsbury's Kiwi Fruit, Ripe & Ready x4";
        String description = "Kiwi";
        String pricePerUnit = "12.20";
        double pageSize = 38.6454545;
        String detailsUrl = "http://www.detailsurl.cm/some.html";

        Product product = new Product(title, detailsUrl, pricePerUnit, pageSize, description);

        // When
        String actualSize = product.getSize();

        // Then
        assertThat(actualSize).isEqualTo("38.6Kb");

    }

    @Test
    public void should_remove_pound_sign_and_covert_to_unit_price() throws Exception {

        // Given
        String title = "Sainsbury's Kiwi Fruit, Ripe & Ready x4";
        String description = "Kiwi";
        String pricePerUnit = "&pound3.50";
        double pageSize = 38.6;
        String detailsUrl = "http://www.detailsurl.cm/some.html";

        BigDecimal expectedUnitPrice = new BigDecimal("3.50");

        Product product = new Product(title, detailsUrl, pricePerUnit, pageSize, description);

        // When
        BigDecimal actualUnitPrice = product.getUnitPrice();

        // Then
        assertThat(actualUnitPrice).isEqualTo(expectedUnitPrice);

    }

    @Test
    public void should_return_null_if_price_per_unit_is_null() throws Exception {

        // Given
        String title = "Sainsbury's Kiwi Fruit, Ripe & Ready x4";
        String description = "Kiwi";
        double pageSize = 38.6;
        String detailsUrl = "http://www.detailsurl.cm/some.html";

        Product product = new Product(title, detailsUrl, null, pageSize, description);

        // When
        BigDecimal actualUnitPrice = product.getUnitPrice();

        // Then
        assertThat(actualUnitPrice).isNull();

    }

}