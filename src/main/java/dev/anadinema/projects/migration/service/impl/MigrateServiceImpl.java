package dev.anadinema.projects.migration.service.impl;

import dev.anadinema.projects.migration.model.TickTickDataObject;
import dev.anadinema.projects.migration.service.MigrateService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MigrateServiceImpl implements MigrateService {

    @Override
    public ResponseEntity<Resource> trasformData(String user, Boolean tagBasedProject, List<TickTickDataObject> tickTickDataObjectList, String projectSeparatedByTags, String tagsExcludedFromLabels) {
        return null;
    }
    
}
