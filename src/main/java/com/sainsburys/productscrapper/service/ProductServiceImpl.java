package com.sainsburys.productscrapper.service;

import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sainsburys.productscrapper.constants.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Parser htmlParser;

    @Override
    public List<Product> getAll() {

        Elements allProducts = htmlParser.getAllProducts(SCRAPE_URL);

        if (allProducts == null) {
            return null;
        }

        List<Product> products = allProducts.stream().map(p -> {
            String title = p.select(TITLE).text();
            String detailsUrl = p.select(DETAILS_URL).attr(HREF);
            String pricePerUnit = p.getElementsByClass(PRICE_PER_UNIT).get(0).ownText();

            double pageSize = htmlParser.getPageSize(detailsUrl);
            String description = htmlParser.getProductDescription(detailsUrl);

            Product product = new Product(title, detailsUrl, pricePerUnit, pageSize, description);

            return product;

        }).collect(Collectors.toList());

        return products;
    }

}
