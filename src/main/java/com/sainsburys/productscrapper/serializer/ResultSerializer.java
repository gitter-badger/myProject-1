package com.sainsburys.productscrapper.serializer;

import com.sainsburys.productscrapper.model.Result;

public interface ResultSerializer {
    String serialize(Result result);
}
