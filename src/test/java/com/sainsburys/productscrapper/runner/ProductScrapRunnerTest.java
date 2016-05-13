package com.sainsburys.productscrapper.runner;

import com.sainsburys.productscrapper.builders.ProductBuilder;
import com.sainsburys.productscrapper.facade.ResultsFacade;
import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.reporter.Reporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductScrapRunnerTest {

    @Mock
    private ResultsFacade resultsFacade;

    @Mock
    private Reporter consoleReporter;

    @InjectMocks
    private ProductScrapRunner productScrapRunner = new ProductScrapRunner();

    @Test
    public void should_call_resultsFacade_to_get_results() throws Exception {

        // Arrange
        Product product1 = new ProductBuilder().withPageSize(10.1).build();
        Product product2 = new ProductBuilder().withPageSize(20.2).build();
        Product product3 = new ProductBuilder().withPageSize(30).build();
        List<Product> products = asList(product1, product2, product3);

        Result result = new Result(products);

        when(resultsFacade.getResults()).thenReturn(result);

        // Act
        productScrapRunner.run();

        // Assert
        verify(resultsFacade).getResults();

    }

    @Test
    public void should_pass_the_results_to_console_reporter() throws Exception {

        // Arrange
        Product product1 = new ProductBuilder().withPageSize(10.1).build();
        Product product2 = new ProductBuilder().withPageSize(20.2).build();
        Product product3 = new ProductBuilder().withPageSize(30).build();
        List<Product> products = asList(product1, product2, product3);

        Result result = new Result(products);

        when(resultsFacade.getResults()).thenReturn(result);

        // Act
        productScrapRunner.run();

        ArgumentCaptor<Result> resultCaptor = ArgumentCaptor.forClass(Result.class);

        // Assert
        verify(consoleReporter, times(1)).report(resultCaptor.capture());
        Result actualResult = resultCaptor.getValue();

        assertThat(actualResult.getResults().size()).isEqualTo(3);

    }
}