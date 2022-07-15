package sk.best.newtify.backend.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import sk.best.newtify.api.dto.DollarPriceDTO;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author Arthur Noel
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
@Service
public class DollarpriceService {

    @Value("classpath:dollarprice.json")
    Resource resourceFile;

    private static JSONObject jsonObject;

    public DollarPriceDTO retrieveDollarPrice(Integer price) {
        Integer dayPrice;

        // read JSON file and map/convert to java POJO
        try {
            if (jsonObject == null) {
                jsonObject = parse();
            }

            // retrieve price from json object
            Integer actualPrice = 1;
            JSONObject priceList = (JSONObject) jsonObject.get(actualPrice);
            dayPrice = (Integer) priceList.get(price);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        DollarPriceDTO dollarPriceDTO = new DollarPriceDTO();
        dollarPriceDTO.setPrice(dayPrice);
        return dollarPriceDTO;
    }

    private JSONObject parse() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new FileReader(resourceFile.getFile()));
    }

}
