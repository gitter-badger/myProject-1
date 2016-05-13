package com.sainsburys.productscrapper.facade;

import com.sainsburys.productscrapper.builders.ProductBuilder;
import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

@RunWith(MockitoJUnitRunner.class)
public class ResultsFacadeImplTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ResultsFacade resultsFacade = new ResultsFacadeImpl();

    @Test
    public void should_return_results_when_productRepository_returns_some_products() throws Exception {

        // Given
        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();

        List<Product> products = asList(product1, product2, product3);
        when(productService.getAll()).thenReturn(products);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThat(result).isNotNull();
        assertThatJson(result).node("results").isPresent();
    }

    @Test
    public void should_return_same_product_in_json_as_returned_by_productRepository() throws Exception {

        // Given
        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();

        List<Product> products = asList(product1, product2, product3);
        when(productService.getAll()).thenReturn(products);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThatJson(result).node("results").isArray().ofLength(3);
    }

    @Test
    public void should_return_total() throws Exception {

        // Given
        Product product1 = new ProductBuilder().withPricePerUnit("22.9").build();
        Product product2 = new ProductBuilder().withPricePerUnit("32.9").build();
        Product product3 = new ProductBuilder().withPricePerUnit("42.9").build();

        List<Product> products = asList(product1, product2, product3);
        when(productService.getAll()).thenReturn(products);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThatJson(result).node("total").isEqualTo(98.7);
    }

    @Test
    public void should_convert_all_products_titles_to_json_with_correct_values() throws Exception {

        // Given
        Product product1 = new ProductBuilder().withTitle("title1").build();
        Product product2 = new ProductBuilder().withTitle("title2").build();
        Product product3 = new ProductBuilder().withTitle("title3").build();

        List<Product> products = asList(product1, product2, product3);
        when(productService.getAll()).thenReturn(products);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThatJson(result).node("results[0].title").isEqualTo("title1");
        assertThatJson(result).node("results[1].title").isEqualTo("title2");
        assertThatJson(result).node("results[2].title").isEqualTo("title3");
    }

    @Test
    public void should_convert_all_products_description_to_json_with_correct_values() throws Exception {

        // Given
        Product product1 = new ProductBuilder().withDescription("description 1").build();
        Product product2 = new ProductBuilder().withDescription("description 2").build();
        Product product3 = new ProductBuilder().withDescription("description 3").build();

        List<Product> products = asList(product1, product2, product3);
        when(productService.getAll()).thenReturn(products);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThatJson(result).node("results[0].description").isEqualTo("description 1");
        assertThatJson(result).node("results[1].description").isEqualTo("description 2");
        assertThatJson(result).node("results[2].description").isEqualTo("description 3");
    }

    @Test
    public void should_convert_all_products_size_to_json_with_correct_values() throws Exception {

        // Given
        Product product1 = new ProductBuilder().withPageSize(10.1).build();
        Product product2 = new ProductBuilder().withPageSize(20.2).build();
        Product product3 = new ProductBuilder().withPageSize(30).build();

        List<Product> products = asList(product1, product2, product3);
        when(productService.getAll()).thenReturn(products);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThatJson(result).node("results[0].size").isEqualTo("10.1Kb");
        assertThatJson(result).node("results[1].size").isEqualTo("20.2Kb");
        assertThatJson(result).node("results[2].size").isEqualTo("30Kb");
    }

    @Test
    public void should_convert_all_products_unit_price_to_json_with_correct_values() throws Exception {

        // Given
        Product product1 = new ProductBuilder().withPricePerUnit("12.20").build();
        Product product2 = new ProductBuilder().withPricePerUnit("112.20").build();
        Product product3 = new ProductBuilder().withPricePerUnit("212.20").build();

        List<Product> products = asList(product1, product2, product3);
        when(productService.getAll()).thenReturn(products);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThatJson(result).node("results[0].unit_price").isEqualTo("12.2");
        assertThatJson(result).node("results[1].unit_price").isEqualTo("112.2");
        assertThatJson(result).node("results[2].unit_price").isEqualTo("212.2");
    }

    @Test
    public void should_return_no_products_when_productRepository_returns_no_products() throws Exception {

        // Given
        when(productService.getAll()).thenReturn(null);

        // When
        String result = resultsFacade.getResults();

        // Then
        assertThatJson(result).isEqualTo("{}");
    }

}