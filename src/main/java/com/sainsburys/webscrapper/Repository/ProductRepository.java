package com.sainsburys.webscrapper.Repository;

import com.sainsburys.webscrapper.model.Product;

import java.util.List;

public interface ProductRepository {

   List<Product> getAll();
}
