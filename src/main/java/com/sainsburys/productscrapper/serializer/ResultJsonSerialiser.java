package com.sainsburys.productscrapper.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.productscrapper.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ResultJsonSerialiser implements ResultSerialiser {

    private Logger LOG = LoggerFactory.getLogger(ResultJsonSerialiser.class);

    private final ObjectMapper mapper;

    public ResultJsonSerialiser(ObjectMapper objectMapper){
        this.mapper = objectMapper;
    }

    @Override
    public String serialize(Result result) {

        if(result == null) {
            return EMPTY;
        }

        String resultAsJson = EMPTY;

        try {
            resultAsJson = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            LOG.error("Error serialising results to JSON", e);
        }

        return resultAsJson;
    }
}
