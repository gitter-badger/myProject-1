package com.sainsburys.webscrapper.repository;

import com.sainsburys.webscrapper.model.Product;

import java.util.List;

public interface ProductRepository {

   List<Product> getAll();
}
