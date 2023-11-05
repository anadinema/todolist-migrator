package dev.anadinema.projects.migration.service.impl;

import dev.anadinema.projects.migration.exception.InvalidRequestException;
import dev.anadinema.projects.migration.mapper.TickTickDataToTodoistCsvPojoMapper;
import dev.anadinema.projects.migration.mapper.file.PojoToCsvMapper;
import dev.anadinema.projects.migration.model.TickTickDataObject;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import dev.anadinema.projects.migration.service.MigrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MigrateServiceImpl implements MigrateService {

    @Autowired
    private TickTickDataToTodoistCsvPojoMapper tickDataToTodoistCsvPojoMapper;

    @Override
    public ResponseEntity<Resource> trasformData(String user, Boolean tagBasedProject, List<TickTickDataObject> tickTickDataObjectList, String projectSeparatedByTags) {
        validate(tagBasedProject, projectSeparatedByTags);
        List<TodoistCsvData> todoistCsvData = tickDataToTodoistCsvPojoMapper
                .generateCsvPojo(tickTickDataObjectList, user, tagBasedProject, projectSeparatedByTags);
        if (todoistCsvData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PojoToCsvMapper.mapToTodoistCsv(todoistCsvData), HttpStatus.OK);
    }

    private void validate(boolean tagBasedProject, String projectSeparatedByTags) {
        if (tagBasedProject) {
            if (Optional.ofNullable(projectSeparatedByTags).isEmpty() || projectSeparatedByTags.isBlank() ) {
                throw new InvalidRequestException("tagBasedProject creation was asked for, but which tags to use was not specified!");
            }
        }
    }
    
}
