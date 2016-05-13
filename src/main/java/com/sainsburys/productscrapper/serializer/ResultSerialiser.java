package com.sainsburys.productscrapper.serializer;

import com.sainsburys.productscrapper.model.Result;

public interface ResultSerialiser {
    String serialize(Result result);
}
