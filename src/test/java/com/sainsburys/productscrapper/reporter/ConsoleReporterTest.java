package com.sainsburys.productscrapper.reporter;

import com.sainsburys.productscrapper.builders.ProductBuilder;
import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.serializer.ResultSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleReporterTest {

    @Mock
    private ResultSerializer resultSerializer;

    @InjectMocks
    private Reporter consoleReporter = new ConsoleReporter();

    @Test
    public void should_call_json_serializer_to_serialize_result() throws Exception {

        // Arrange
        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();
        String serializedJson = "{\"results\" : [ { \"title\" : \"title1\"}]}";

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        when(resultSerializer.serialize(result)).thenReturn(serializedJson);

        // Act
        consoleReporter.report(result);

        // Assert
        verify(resultSerializer).serialize(result);
    }

}