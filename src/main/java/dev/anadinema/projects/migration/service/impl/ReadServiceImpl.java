package dev.anadinema.projects.migration.service.impl;

import dev.anadinema.projects.migration.exception.FailedParseException;
import dev.anadinema.projects.migration.mapper.file.CsvToPojoMapper;
import dev.anadinema.projects.migration.mapper.TickTickCsvPojoToDataMapper;
import dev.anadinema.projects.migration.mapper.TodoistCsvPojoToDataMapper;
import dev.anadinema.projects.migration.model.TickTickCsvData;
import dev.anadinema.projects.migration.model.TickTickDataObject;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import dev.anadinema.projects.migration.model.TodoistDataObject;
import dev.anadinema.projects.migration.service.ReadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ReadServiceImpl implements ReadService {

    @Autowired
    TickTickCsvPojoToDataMapper tickTickDataObjectFunction;

    @Autowired
    TodoistCsvPojoToDataMapper todoistDataObjectFunction;

    @Override
    public ResponseEntity<List<TickTickDataObject>> parseTicktickCsv(Resource data) {
        List<TickTickCsvData> csvDataList = new ArrayList<>();
        if (data.exists() && data.isFile() && data.isReadable()) {
            try {
                csvDataList = CsvToPojoMapper.mapToTickTickCsvPojo(data.getFile().toPath());
            } catch (IOException ioException) {
                log.error("Failed to get file from path due to : {}", ioException.getMessage());
                log.debug(ioException);
                throw new FailedParseException(ioException.getMessage());
            }
        }

        if (csvDataList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tickTickDataObjectFunction.apply(csvDataList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TodoistDataObject>> parseTodoistCsv(Resource data) {
        List<TodoistCsvData> csvDataList = new ArrayList<>();
        if (data.exists() && data.isFile() && data.isReadable()) {
            try {
                csvDataList = CsvToPojoMapper.mapToTodoistCsvPojo(data.getFile().toPath());
            } catch (IOException ioException) {
                log.error("Failed to get file from path due to : {}", ioException.getMessage());
                log.debug(ioException);
                throw new FailedParseException(ioException.getMessage());
            }
        }

        if (csvDataList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todoistDataObjectFunction.apply(csvDataList), HttpStatus.OK);
    }

}
