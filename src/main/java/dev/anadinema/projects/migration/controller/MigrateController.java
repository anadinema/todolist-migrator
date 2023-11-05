package dev.anadinema.projects.migration.controller;

import dev.anadinema.projects.migration.model.TickTickDataObject;
import dev.anadinema.projects.migration.service.MigrateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
public class MigrateController implements dev.anadinema.projects.migration.api.MigrateApi {

    @Autowired
    private MigrateService  migrateService;

    @Override
    public ResponseEntity<Resource> transformForDestination(String user, Boolean tagBasedProject, List<TickTickDataObject> tickTickDataObjectList, String projectSeparatedByTags) {
        return migrateService.trasformData(user, tagBasedProject, tickTickDataObjectList, projectSeparatedByTags);
    }

}
