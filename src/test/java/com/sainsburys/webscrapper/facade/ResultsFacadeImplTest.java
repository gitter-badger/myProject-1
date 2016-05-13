package com.sainsburys.webscrapper.facade;

import com.sainsburys.webscrapper.repository.ProductRepository;
import com.sainsburys.webscrapper.builders.ProductBuilder;
import com.sainsburys.webscrapper.model.Product;
import com.sainsburys.webscrapper.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultsFacadeImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ResultsFacade resultsFacade = new ResultsFacadeImpl();

    @Test
    public void should_return_results_when_productRepository_returns_some_products() throws Exception {

        // Given
        Product product1 = new ProductBuilder().withTitle("title1").withPricePerUnit("22.9").build();
        Product product2 = new ProductBuilder().withTitle("title1").withPricePerUnit("32.9").build();
        Product product3 = new ProductBuilder().withTitle("title1").withPricePerUnit("42.9").build();

        List<Product> products = asList(product1, product2, product3);
        when(productRepository.getAll()).thenReturn(products);

        // When
        Result result = resultsFacade.getResults();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getResults().size()).isEqualTo(3);
    }

    @Test
    public void should_return_no_products_when_productRepository_returns_no_products() throws Exception {

        // Given
        when(productRepository.getAll()).thenReturn(null);

        // When
        Result result = resultsFacade.getResults();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getResults()).isNull();
    }

}