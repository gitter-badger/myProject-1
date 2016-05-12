package com.sainsburys.webscrapper.model;

import com.sainsburys.webscrapper.builders.ProductBuilder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class ResultTest {

    @Test
    public void should_get_total_of_price_per_unit() {

        // Given
        Product product1 = new ProductBuilder().withPricePerUnit("10.0").build();
        Product product2 = new ProductBuilder().withPricePerUnit("20.9").build();
        Product product3 = new ProductBuilder().withPricePerUnit("55.1").build();

        List<Product> products = asList(product1, product2, product3);

        BigDecimal expectedTotal = new BigDecimal("86.0");

        Result result = new Result(products);

        // When
        BigDecimal actualTotal = result.getTotal();

        // Then
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }

    @Test
    public void should_return_null_for_total_if_no_products() throws Exception {

        // Given
        Result result = new Result(null);

        // When
        BigDecimal actualTotal = result.getTotal();

        // Then
        assertThat(actualTotal).isNull();
    }

    @Test
    public void should_return_zero_as_total_if_not_producct_have_price_per_unit_specified() {

        // Given
        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();

        List<Product> products = asList(product1, product2, product3);

        Result result = new Result(products);

        // When
        BigDecimal actualTotal = result.getTotal();

        // Then
        assertThat(actualTotal).isZero();
    }
}