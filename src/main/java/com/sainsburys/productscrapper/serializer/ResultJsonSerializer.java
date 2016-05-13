package com.sainsburys.productscrapper.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sainsburys.productscrapper.model.Result;
import org.apache.log4j.Logger;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ResultJsonSerializer implements ResultSerializer {

    private Logger LOG = Logger.getLogger(ResultJsonSerializer.class);

    @Override
    public String serialize(Result result) {

        if(result == null) {
            return EMPTY;
        }

        String resultAsJson = EMPTY;
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            resultAsJson = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            LOG.error("error converting results to json", e);
        }

        return resultAsJson;
    }
}
