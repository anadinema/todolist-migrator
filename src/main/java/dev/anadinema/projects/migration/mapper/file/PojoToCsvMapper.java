package dev.anadinema.projects.migration.mapper.file;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import dev.anadinema.projects.migration.exception.FailedParseException;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Log4j2
public class PojoToCsvMapper {

    private static final String OUTPUT_FILE = "output.csv";

    public static Resource mapToTodoistCsv(List<TodoistCsvData> todoistCsvDataList) {
        try(Writer writer  = new FileWriter(OUTPUT_FILE)) {
            StatefulBeanToCsv<List<TodoistCsvData>> beanToCsv = new StatefulBeanToCsvBuilder<List<TodoistCsvData>>(writer)
                    .withOrderedResults(false)
                    .build();
            beanToCsv.write(todoistCsvDataList);
            return new PathResource(OUTPUT_FILE);
        } catch (IOException ioException) {
            log.error("Failed to write file due to : {}", ioException.getMessage());
            log.debug(ioException);
            throw new FailedParseException(ioException.getMessage());
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException csvException) {
            log.error("Either required field is empty or having mismatched type, failing generating the CSV!");
            log.debug(csvException);
            throw new FailedParseException(csvException.getMessage());
        }
    }

}
