package dev.anadinema.projects.migration.mapper;

import dev.anadinema.projects.migration.util.TickTickDataUtils;
import dev.anadinema.projects.migration.model.TickTickCsvData;
import dev.anadinema.projects.migration.model.TickTickDataObject;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class TickTickCsvPojoToDataMapper implements Function<List<TickTickCsvData>, List<TickTickDataObject>> {

    @Override
    public List<TickTickDataObject> apply(List<TickTickCsvData> tickTickCsvDataList) {
        List<TickTickDataObject> tickTickDataObjectList = new ArrayList<>();
        tickTickCsvDataList.stream()
                .filter(tickTickCsvData -> !tickTickCsvData.getKind().isBlank())
                .filter(tickTickCsvData -> (tickTickCsvData.getKind().equalsIgnoreCase(TickTickDataUtils.TEXT_KIND)
                        || tickTickCsvData.getKind().equalsIgnoreCase(TickTickDataUtils.CHECK_LIST_KIND)))
                .filter(tickTickCsvData -> tickTickCsvData.getStatus() == 0)
                .filter(tickTickCsvData -> !tickTickCsvData.getTitle().isBlank())
                .forEach(tickTickCsvData -> tickTickDataObjectList.add(map(tickTickCsvData)));
        return tickTickDataObjectList;
    }

    private TickTickDataObject map(TickTickCsvData tickTickCsvData) {
        TickTickDataObject mappedData = new TickTickDataObject();

        mappedData.setTitle(tickTickCsvData.getTitle());
        mappedData.setContent(Optional.ofNullable(tickTickCsvData.getContent()).orElse(""));
        mappedData.setKind(tickTickCsvData.getKind());
        mappedData.setPriority(BigDecimal.valueOf(tickTickCsvData.getPriority()));
        if (Optional.ofNullable(tickTickCsvData.getTags()).isPresent()) {
            mappedData.setTags(Arrays.asList(tickTickCsvData.getTags().split(", ")));
        }
        mappedData.setRepeat(tickTickCsvData.getRepeat());
        mappedData.setStatus(BigDecimal.valueOf(tickTickCsvData.getStatus()));

        return mappedData;

    }

}
