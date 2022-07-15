package sk.best.newtify.web.connector;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.best.newtify.api.DollarpriceApi;
import sk.best.newtify.api.dto.DollarPriceDTO;

/**
 * @author Arthur Noel
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
@Slf4j
@Service
public class DollarPriceConnectorService implements DollarpriceApi {

    private static final String DOLLARPRICE_API_URL = "http://localhost:8081/v1/dollarprice?price={price}";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<DollarPriceDTO> retrieveDollarPrice(@NonNull Integer price) {
        try {
            return restTemplate.getForEntity(DOLLARPRICE_API_URL, DollarPriceDTO.class, price);
        } catch (Exception e) {
            log.error("ERROR retrieveDollarPrice", e);
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

}
