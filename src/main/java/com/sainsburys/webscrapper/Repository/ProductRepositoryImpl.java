package com.sainsburys.webscrapper.Repository;

import com.sainsburys.webscrapper.Parser.Parser;
import com.sainsburys.webscrapper.model.Product;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductRepositoryImpl implements ProductRepository {

    private static final String TITLE = "div.productInfo a";
    private static final String DETAILS_URL = "div.productInfo a";
    private static final String HREF = "href";
    private static final String PRICE_PER_UNIT = "pricePerUnit";

    @Autowired
    private Parser htmlParser;

    @Override
    public List<Product> getAll() {

        Elements allProducts = htmlParser.getAllProducts();

        if (allProducts == null) {
            return null;
        }

        List<Product> products = allProducts.stream()
                .map(p -> {

                    String title = p.select(TITLE).text();
                    String detailsUrl = p.select(DETAILS_URL).attr(HREF);
                    String pricePerUnit = p.getElementsByClass(PRICE_PER_UNIT).get(0).ownText();

                    double pageSize = htmlParser.getPageSize(detailsUrl);
                    String description = htmlParser.getProductDescription(detailsUrl);

                    Product product = new Product(title, detailsUrl, pricePerUnit, pageSize, description);

                    return product;

                })
                .collect(Collectors.toList());

        return products;
    }

}
