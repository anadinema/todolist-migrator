package dev.anadinema.projects.migration.mapper.file;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import dev.anadinema.projects.migration.exception.FailedParseException;
import dev.anadinema.projects.migration.model.TickTickCsvData;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Log4j2
public class CsvToPojoMapper {

    public static List<TickTickCsvData> mapToTickTickCsvPojo(InputStream inputStream) {
        try(Reader reader = new InputStreamReader(inputStream)) {
            return new CsvToBeanBuilder<TickTickCsvData>(reader)
                    .withOrderedResults(false)
                    .withSkipLines(6)
                    .withSeparator(',')
                    .withQuoteChar('"')
                    .withEscapeChar('\n')
                    .withType(TickTickCsvData.class)
                    .build().parse();
        } catch (IOException ioException) {
            log.error("Failed to parse/transform file due to : {}", ioException.getMessage());
            log.debug(ioException);
            throw new FailedParseException(ioException.getMessage());
        }
    }

    public static List<TodoistCsvData> mapToTodoistCsvPojo(InputStream inputStream) {
        try(Reader reader = new InputStreamReader(inputStream)) {
            return new CsvToBeanBuilder<TodoistCsvData>(reader)
                    .withOrderedResults(false)
                    .withSeparator(',')
                    .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withType(TodoistCsvData.class)
                    .build().parse();
        } catch (IOException ioException) {
            log.error("Failed to parse/transform file due to : {}", ioException.getMessage());
            log.debug(ioException);
            throw new FailedParseException(ioException.getMessage());
        }
    }

}
