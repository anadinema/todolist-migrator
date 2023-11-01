package dev.anadinema.projects.migration.service;

import dev.anadinema.projects.migration.model.TickTickDataObject;
import dev.anadinema.projects.migration.model.TodoistDataObject;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ReadService {

    ResponseEntity<List<TickTickDataObject>> parseTicktickCsv(Resource data);

    ResponseEntity<List<TodoistDataObject>> parseTodoistCsv(Resource data);

}
