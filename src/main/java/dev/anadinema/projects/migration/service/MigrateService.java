package dev.anadinema.projects.migration.service;

import dev.anadinema.projects.migration.model.TickTickDataObject;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MigrateService {

    ResponseEntity<Resource> trasformData(String user, Boolean tagBasedProject, List<TickTickDataObject> tickTickDataObjectList, String projectSeparatedByTags);

}
