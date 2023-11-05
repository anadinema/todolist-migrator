package dev.anadinema.projects.migration.controller;

import dev.anadinema.projects.migration.api.ReadApi;
import dev.anadinema.projects.migration.model.TickTickDataObject;
import dev.anadinema.projects.migration.model.TodoistDataObject;
import dev.anadinema.projects.migration.service.ReadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
public class ReadController implements ReadApi {

    @Autowired
    private ReadService readService;

    @Override
    public ResponseEntity<List<TickTickDataObject>> parseTicktickCsv(Resource body) {
        return readService.parseTicktickCsv(body);
    }

    @Override
    public ResponseEntity<List<TodoistDataObject>> parseTodoistCsv(Resource body) {
        return readService.parseTodoistCsv(body);
    }

}
