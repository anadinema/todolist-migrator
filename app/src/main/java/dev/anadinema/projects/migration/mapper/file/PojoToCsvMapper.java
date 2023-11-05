package dev.anadinema.projects.migration.mapper.file;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import dev.anadinema.projects.migration.exception.FailedParseException;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

@Log4j2
public class PojoToCsvMapper {

    private static final String OUTPUT_FILE = "output.csv";
    private static final String COMMA_DELIMITER = ",";
    private static final String NEWLINE = "\n";
    private static final String HEADER_ROW = "TYPE,CONTENT,DESCRIPTION,PRIORITY,INDENT,AUTHOR,RESPONSIBLE,DATE,DATE_LANG,TIMEZONE,DURATION,DURATION_UNIT";


    public static Resource mapToTodoistCsv(List<TodoistCsvData> todoistCsvDataList) {
        Resource resource = new ClassPathResource("/data/output.csv");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(resource.getFile()))) {
            writer.write(HEADER_ROW);
            writer.append(NEWLINE);
            for (TodoistCsvData todoistCsvData : todoistCsvDataList) {
                writer.append(getDelimitedString(todoistCsvData));
            }
            return resource;
        } catch (IOException ioException) {
            log.error("Failed to write file due to : {}", ioException.getMessage());
            log.debug(ioException);
            throw new FailedParseException(ioException.getMessage());
        }
    }

    private static String getDelimitedString(TodoistCsvData todoistCsvData) {
        return new StringBuilder()
                .append(Optional.ofNullable(todoistCsvData.getType()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getContent()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getDescription()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getPriority()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getIndent()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getAuthor()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getResponsible()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getDate()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getDateLang()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getTimezone()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getDuration()).orElse(""))
                .append(COMMA_DELIMITER)
                .append(Optional.ofNullable(todoistCsvData.getDurationUnit()).orElse(""))
                .append(NEWLINE)
                .toString();
    }

/*    public static Resource mapToTodoistCsv(List<TodoistCsvData> todoistCsvDataList) {
        Resource resource = new ClassPathResource("/data/output.csv");
        try(Writer writer  = new FileWriter(resource.getFile())) {
            StatefulBeanToCsv<List<TodoistCsvData>> beanToCsv = new StatefulBeanToCsvBuilder<List<TodoistCsvData>>(writer)
                    .withApplyQuotesToAll(false)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false)
                    .build();
            beanToCsv.write(todoistCsvDataList);
            return resource;
        } catch (IOException ioException) {
            log.error("Failed to write file due to : {}", ioException.getMessage());
            log.debug(ioException);
            throw new FailedParseException(ioException.getMessage());
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException csvException) {
            log.error("Either required field is empty or having mismatched type, failing generating the CSV!");
            log.debug(csvException);
            throw new FailedParseException(csvException.getMessage());
        }
    }*/

}
