package dev.anadinema.projects.migration.controller;

import dev.anadinema.projects.migration.model.HealthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ManageController implements dev.anadinema.projects.migration.api.ManageApi {

    @Override
    public ResponseEntity<HealthResponse> performHealthCheck() {
        return null;
    }

}
