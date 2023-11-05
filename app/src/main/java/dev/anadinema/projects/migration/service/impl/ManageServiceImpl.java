package dev.anadinema.projects.migration.service.impl;

import dev.anadinema.projects.migration.model.HealthResponse;
import dev.anadinema.projects.migration.model.HealthResponseDetails;
import dev.anadinema.projects.migration.model.StatusEnum;
import dev.anadinema.projects.migration.service.ManageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Value(value = "${management.health.ping.base-uri:}")
    private String baseUri;

    @Override
    public ResponseEntity<HealthResponse> performHealthCheck() {
        HealthResponse healthResponse = new HealthResponse();
        HealthResponseDetails healthResponseDetails = new HealthResponseDetails();

        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(baseUri)
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(500))
                .build();

        ResponseEntity<String> response = restTemplate.getForEntity("/ping", String.class);

        if (response.getStatusCode().is2xxSuccessful() && response.hasBody()
                && Optional.ofNullable(response.getBody()).isPresent()
                && response.getBody().equalsIgnoreCase("pong")) {
            healthResponse.setStatus(StatusEnum.UP);
            healthResponseDetails.setApi(StatusEnum.UP);
            healthResponseDetails.setGui(StatusEnum.MAINTENANCE);
            healthResponseDetails.setEngine(StatusEnum.UP);
        } else if (response.getStatusCode() == HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS) {
            healthResponse.setStatus(StatusEnum.MAINTENANCE);
            healthResponseDetails.setApi(StatusEnum.MAINTENANCE);
            healthResponseDetails.setGui(StatusEnum.MAINTENANCE);
            healthResponseDetails.setEngine(StatusEnum.MAINTENANCE);
        } else {
            healthResponse.setStatus(StatusEnum.DOWN);
            healthResponseDetails.setApi(StatusEnum.DOWN);
            healthResponseDetails.setGui(StatusEnum.MAINTENANCE);
            healthResponseDetails.setEngine(StatusEnum.DOWN);
        }
        healthResponse.setDetails(healthResponseDetails);
        return new ResponseEntity<>(healthResponse, HttpStatus.OK);
    }

}
