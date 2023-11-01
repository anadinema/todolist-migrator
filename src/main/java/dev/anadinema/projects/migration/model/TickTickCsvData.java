package dev.anadinema.projects.migration.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TickTickCsvData {
    //Folder Name	List Name	Title	Kind	Tags	Content	Is Check list	Start Date	Due Date
    // Reminder	Repeat	Priority	Status	Created Time	Completed Time	Order	Timezone	Is All Day
    // Is Floating	Column Name	Column Order	View Mode	taskId	parentId

    @CsvBindByName(column = "Title")
    private String title;

    @CsvBindByName(column = "Kind")
    private String kind;

    @CsvBindByName(column = "Tags")
    private String tags;

    @CsvBindByName(column = "Content")
    private String content;

    @CsvBindByName(column = "Is Check list")
    private String isChecklist;

    @CsvBindByName(column = "Repeat")
    private String repeat;

    @CsvBindByName(column = "Priority")
    private int priority;

    @CsvBindByName(column = "Status")
    private int status;

}
