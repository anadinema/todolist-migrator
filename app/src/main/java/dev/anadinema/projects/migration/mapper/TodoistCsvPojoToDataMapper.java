package dev.anadinema.projects.migration.mapper;

import dev.anadinema.projects.migration.util.TodoistDataUtils;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import dev.anadinema.projects.migration.model.TodoistDataObject;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class TodoistCsvPojoToDataMapper implements Function<List<TodoistCsvData>, List<TodoistDataObject>> {

    @Override
    public List<TodoistDataObject> apply(List<TodoistCsvData> todoistCsvDataList) {
        List<TodoistDataObject> todoistDataObjectList = new ArrayList<>();
        todoistCsvDataList.stream()
                .filter(todoistCsvData -> Optional.ofNullable(todoistCsvData.getType()).isPresent())
                .filter(todoistCsvData -> todoistCsvData.getType().equalsIgnoreCase(TodoistDataUtils.VALID_TASK_KIND))
                .filter(todoistCsvData -> Optional.ofNullable(todoistCsvData.getContent()).isPresent())
                .filter(todoistCsvData -> !todoistCsvData.getContent().isBlank())
                .forEach(todoistCsvData -> todoistDataObjectList.add(map(todoistCsvData)));
        return todoistDataObjectList;
    }

    private TodoistDataObject map(TodoistCsvData todoistCsvData) {
        TodoistDataObject mappedData = new TodoistDataObject();

        mappedData.setContent(todoistCsvData.getContent());
        mappedData.setDescription(Optional.ofNullable(todoistCsvData.getDescription()).orElse(""));
        mappedData.setKind(todoistCsvData.getType());
        if (Optional.ofNullable(todoistCsvData.getPriority()).isPresent() && !todoistCsvData.getPriority().isBlank()) {
            mappedData.setPriority(Integer.valueOf(todoistCsvData.getPriority()));
        }
        mappedData.setDate(todoistCsvData.getDate());
        mappedData.setDateLang(todoistCsvData.getDateLang());
        mappedData.setResponsible(todoistCsvData.getResponsible());

        return mappedData;

    }

}
