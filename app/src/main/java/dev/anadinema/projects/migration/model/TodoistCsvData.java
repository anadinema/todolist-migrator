package dev.anadinema.projects.migration.model;

import com.opencsv.bean.CsvBindByName;
import dev.anadinema.projects.migration.util.TodoistDataUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoistCsvData {

    @CsvBindByName(column = "TYPE")
    private String type = TodoistDataUtils.VALID_TASK_KIND;

    @CsvBindByName(column = "CONTENT")
    private String content;

    @CsvBindByName(column = "DESCRIPTION")
    private String description;

    @CsvBindByName(column = "PRIORITY")
    private String priority;

    @CsvBindByName(column = "INDENT")
    private String indent;

    @CsvBindByName(column = "AUTHOR")
    private String author;

    @CsvBindByName(column = "RESPONSIBLE")
    private String responsible;

    @CsvBindByName(column = "DATE")
    private String date;

    @CsvBindByName(column = "DATE_LANG")
    private String dateLang;

    @CsvBindByName(column = "TIMEZONE")
    private String timezone;

    @CsvBindByName(column = "DURATION")
    private String duration;

    @CsvBindByName(column = "DURATION_UNIT")
    private String durationUnit;

}
