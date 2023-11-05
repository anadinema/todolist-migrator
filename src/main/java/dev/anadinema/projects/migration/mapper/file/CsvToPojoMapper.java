package dev.anadinema.projects.migration.mapper.file;

import com.opencsv.bean.CsvToBeanBuilder;
import dev.anadinema.projects.migration.exception.FailedParseException;
import dev.anadinema.projects.migration.model.TickTickCsvData;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Log4j2
public class CsvToPojoMapper {

    public static List<TickTickCsvData> mapToTickTickCsvPojo(Path path) {
        try(Reader reader = Files.newBufferedReader(path)) {
            return new CsvToBeanBuilder<TickTickCsvData>(reader)
                    .withOrderedResults(false)
                    .withSkipLines(3)
                    .withType(TickTickCsvData.class)
                    .build().parse();
        } catch (IOException ioException) {
            log.error("Failed to parse/transform file due to : {}", ioException.getMessage());
            log.debug(ioException);
            throw new FailedParseException(ioException.getMessage());
        }
    }

    public static List<TodoistCsvData> mapToTodoistCsvPojo(Path path) {
        try(Reader reader = Files.newBufferedReader(path)) {
            return new CsvToBeanBuilder<TodoistCsvData>(reader)
                    .withOrderedResults(false)
                    .withSkipLines(3)
                    .withType(TodoistCsvData.class)
                    .build().parse();
        } catch (IOException ioException) {
            log.error("Failed to parse/transform file due to : {}", ioException.getMessage());
            log.debug(ioException);
            throw new FailedParseException(ioException.getMessage());
        }
    }

}
