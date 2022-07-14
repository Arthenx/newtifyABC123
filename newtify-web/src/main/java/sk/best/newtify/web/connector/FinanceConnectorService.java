package sk.best.newtify.web.connector;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marek Urban
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
@Slf4j
@Service
public class FinanceConnectorService {
    private static final String FINANCE_API_URL = "http://localhost:8081/v1/finance?cryptoName={cryptoName}";

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<cryptoNameDTO> retrievecryptoName(@NonNull String cryptoName) {
        try {
            return restTemplate.getForEntity(FINANCE_API_URL, cryptoNameDTO.class, cryptoName);
        } catch (Exception e) {
            log.error("ERROR retrievecryptoName", e);
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}
