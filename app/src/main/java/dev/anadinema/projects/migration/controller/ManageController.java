package dev.anadinema.projects.migration.controller;

import dev.anadinema.projects.migration.api.ManageApi;
import dev.anadinema.projects.migration.model.HealthResponse;
import dev.anadinema.projects.migration.service.ManageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ManageController implements ManageApi {

    @Autowired
    private ManageService manageService;

    @GetMapping(path = "/ping")
    public String ping() {
        return "pong";
    }

    @Override
    public ResponseEntity<HealthResponse> performHealthCheck() {
        return manageService.performHealthCheck();
    }

}
