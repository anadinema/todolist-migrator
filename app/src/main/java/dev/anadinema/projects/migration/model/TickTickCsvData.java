package dev.anadinema.projects.migration.model;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class TickTickCsvData {
    //Folder Name	List Name	Title	Kind	Tags	Content	Is Check list	Start Date	Due Date
    // Reminder	Repeat	Priority	Status	Created Time	Completed Time	Order	Timezone	Is All Day
    // Is Floating	Column Name	Column Order	View Mode	taskId	parentId

    @CsvIgnore
    @CsvBindByName(column = "Column Name")
    private String columnName;

    @CsvIgnore
    @CsvBindByName(column = "Column Order")
    private String columnOrder;

    @CsvIgnore
    @CsvBindByName(column = "View Mode")
    private String viewMode;

    @CsvIgnore
    @CsvBindByName(column = "taskId")
    private String taskId;

    @CsvIgnore
    @CsvBindByName(column = "parentId")
    private String parentId;

    @CsvIgnore
    @CsvBindByName(column = "Folder Name")
    private String folderName;

    @CsvIgnore
    @CsvBindByName(column = "List Name")
    private String listName;

    @CsvIgnore
    @CsvBindByName(column = "Start Date")
    private String startDate;

    @CsvIgnore
    @CsvBindByName(column = "Due Date")
    private String dueDate;

    @CsvIgnore
    @CsvBindByName(column = "Reminder")
    private String reminder;

    @CsvIgnore
    @CsvBindByName(column = "Created Time")
    private String createdTime;

    @CsvIgnore
    @CsvBindByName(column = "Completed Time")
    private String completedTime;

    @CsvIgnore
    @CsvBindByName(column = "Order")
    private String order;

    @CsvIgnore
    @CsvBindByName(column = "Timezone")
    private String timezone;

    @CsvIgnore
    @CsvBindByName(column = "Is All Day")
    private String isAllDay;

    @CsvIgnore
    @CsvBindByName(column = "Is Floating")
    private String isFloating;

    @CsvBindByName(column = "Title")
    private String title;

    @CsvBindByName(column = "Kind")
    private String kind;

    @CsvBindAndSplitByName(column = "Tags", elementType = String.class, splitOn = ", ", collectionType = List.class)
    private List<String> tags;

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
