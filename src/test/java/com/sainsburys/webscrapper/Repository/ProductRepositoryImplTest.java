package com.sainsburys.webscrapper.repository;

import com.sainsburys.webscrapper.parser.Parser;
import com.sainsburys.webscrapper.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryImplTest {

    @Mock
    private Parser htmlParser;

    @InjectMocks
    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Test
    public void should_convert_and_products_when_parser_returns_elements() {

        // Given
        when(htmlParser.getAllProducts()).thenReturn(null);

        // When
        List<Product> products = productRepository.getAll();

        // Then
        assertThat(products).isNull();
    }

    @Test
    public void should_return_products_when_parser_not_return_products() {

        // Given
        Elements elements = getProductElements();

        when(htmlParser.getAllProducts()).thenReturn(elements);

        // When
        List<Product> products = productRepository.getAll();

        // Then
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void should_populate_title_from_html() {

        // Given
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts()).thenReturn(elements);

        // When
        List<Product> products = productRepository.getAll();
        Product firstProduct = products.get(0);

        // Then
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getTitle()).isEqualTo("Sainsbury's Apricot Ripe & Ready x5");
    }

    @Test
    public void should_populate_details_url_from_html() {

        // Given
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts()).thenReturn(elements);

        // When
        List<Product> products = productRepository.getAll();
        Product firstProduct = products.get(0);

        // Then
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getDetailsUrl())
                .isEqualTo(
                        "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html");
    }

    @Test
    public void should_populate_price_per_unit_from_html() {

        // Given
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts()).thenReturn(elements);
        BigDecimal expectedUnitPrice = new BigDecimal("3.50");

        // When
        List<Product> products = productRepository.getAll();
        Product firstProduct = products.get(0);

        // Then
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getUnitPrice()).isEqualTo(expectedUnitPrice);
    }

    @Test
    public void should_set_page_size_on_product() {

        // Given
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts()).thenReturn(elements);
        when(htmlParser.getPageSize(anyString())).thenReturn(10.13);

        // When
        List<Product> products = productRepository.getAll();
        Product firstProduct = products.get(0);

        // Then
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getSize()).isEqualTo("10.1Kb");
    }

    @Test
    public void should_set_descripton_on_product() {

        // Given
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts()).thenReturn(elements);
        when(htmlParser.getProductDescription(anyString())).thenReturn("some product description");

        // When
        List<Product> products = productRepository.getAll();
        Product firstProduct = products.get(0);

        // Then
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getDescription()).isEqualTo("some product description");
    }

    private Elements getProductElements() {

        String productsHtml = "<div class=\"product \"> \n"
                + " <div class=\"productInner\"> \n"
                + "  <div class=\"productInfoWrapper\"> \n"
                + "   <div class=\"productInfo\"> \n"
                + "    <h3> <a href=\"http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html\"> Sainsbury's Apricot Ripe &amp; Ready x5 <img src=\"http://c2.sainsburys.co.uk/wcsstore7.11.1.161/SainsburysStorefrontAssetStore/wcassets/product_images/media_7572754_M.jpg\" alt=\"\"> </a> </h3> \n"
                + "\n"
                + "      <div class=\"pricing\"> \n"
                + "       <p class=\"pricePerUnit\"> &amp;pound3.50<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr> </p> \n"
                + "       <p class=\"pricePerMeasure\">&amp;pound0.70<abbr title=\"per\">/</abbr><abbr title=\"each\"><span class=\"pricePerMeasureMeasure\">ea</span></abbr> </p> \n"
                + "      </div> \n" + "      \n" + "    </div> \n" + "   </div> \n" + "  </div> \n" + " </div> \n";

        Document document = Jsoup.parse(productsHtml);

        return document.select("div.product");
    }

}