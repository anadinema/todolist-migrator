package dev.anadinema.projects.migration.service;

import dev.anadinema.projects.migration.model.HealthResponse;
import org.springframework.http.ResponseEntity;

public interface ManageService {

    ResponseEntity<HealthResponse> performHealthCheck();

}
