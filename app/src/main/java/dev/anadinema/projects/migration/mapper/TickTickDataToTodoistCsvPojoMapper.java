package dev.anadinema.projects.migration.mapper;

import dev.anadinema.projects.migration.model.TickTickDataObject;
import dev.anadinema.projects.migration.model.TodoistCsvData;
import dev.anadinema.projects.migration.util.TickTickDataUtils;
import dev.anadinema.projects.migration.util.TodoistDataUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

@Component
public class TickTickDataToTodoistCsvPojoMapper {

    public List<TodoistCsvData> generateCsvPojo(List<TickTickDataObject> tickTickDataObjects, String user, Boolean tagBasedProject, String projectSeparatedByTags) {
        List<TodoistCsvData> todoistCsvDataList = new ArrayList<>();
        Stream<TickTickDataObject> tickTickDataObjectStream = tickTickDataObjects.stream()
                .filter(tickTickDataObject -> !tickTickDataObject.getKind().isBlank())
                .filter(tickTickDataObject -> (tickTickDataObject.getKind().equalsIgnoreCase(TickTickDataUtils.TEXT_KIND)
                        || tickTickDataObject.getKind().equalsIgnoreCase(TickTickDataUtils.CHECK_LIST_KIND)))
                .filter(tickTickDataObject -> tickTickDataObject.getStatus().intValue() == 0)
                .filter(tickTickDataObject -> !tickTickDataObject.getTitle().isBlank());

        if (tagBasedProject) {
            List<String> tagsToInclude = List.of(projectSeparatedByTags.split(";"));
          tickTickDataObjectStream
                  .filter(tickTickDataObject -> !tickTickDataObject.getTags().isEmpty())
                  .filter(tickTickDataObject -> filterTagsForProject(tagsToInclude, tickTickDataObject.getTags()))
                  .forEach(tickTickDataObject -> todoistCsvDataList.add(map(user, tickTickDataObject)));
        } else {
            tickTickDataObjectStream
                    .forEach(tickTickDataObject -> todoistCsvDataList.add(map(user, tickTickDataObject)));
        }
        return todoistCsvDataList;
    }

    private TodoistCsvData map(String user, TickTickDataObject tickTickDataObject) {
        TodoistCsvData mappedData = new TodoistCsvData();

        mappedData.setContent(tickTickDataObject.getTitle());
        mappedData.setDescription(Optional.ofNullable(tickTickDataObject.getContent()).orElse(""));
        mappedData.setType(TodoistDataUtils.VALID_TASK_KIND);
        if (Optional.ofNullable(tickTickDataObject.getTags()).isPresent()) {
            mappedData.setContent(mappedData.getContent() + TodoistDataUtils.TAG_DELIMITER
                    + String.join(TodoistDataUtils.TAG_DELIMITER, tickTickDataObject.getTags()));
        }
        // TODO : Map the priority here
        mappedData.setAuthor(user);
        mappedData.setDateLang(TodoistDataUtils.DEFAULT_DATE_LANG);
        mappedData.setDurationUnit(TodoistDataUtils.DEFAULT_DURATION_UNIT);
        mappedData.setTimezone(Optional.ofNullable(System.getenv(TodoistDataUtils.TIMEZONE_ENV))
                .orElse(TodoistDataUtils.DEFAULT_TIMEZONE));
        mappedData.setIndent(TodoistDataUtils.DEFAULT_INDENT);

        return mappedData;

    }

    private boolean filterTagsForProject(List<String> tagsToBeIncluded, List<String> tagsInTickTickObject) {
        AtomicBoolean result = new AtomicBoolean(false);
        tagsToBeIncluded.forEach(tag ->
                result.set(tagsInTickTickObject.contains(tag)));
        return result.get();
    }

}
