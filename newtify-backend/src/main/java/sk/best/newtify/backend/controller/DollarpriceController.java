package sk.best.newtify.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import sk.best.newtify.api.DollarpriceApi;
import sk.best.newtify.api.dto.DollarPriceDTO;
import sk.best.newtify.backend.service.DollarpriceService;

/**
 * @author Arthur Noel
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */

@Controller
public class DollarpriceController implements DollarpriceApi {

    @Autowired
    private DollarpriceService dollarpriceService;

    @Override
    public ResponseEntity<DollarPriceDTO> retrieveDollarPrice(Integer price) {
        DollarPriceDTO response = dollarpriceService.retrieveDollarPrice(price);
        return ResponseEntity.ok(response);
    }
}
