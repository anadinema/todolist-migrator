package dev.anadinema.projects.migration.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoistDataUtils {

    public static final String VALID_TASK_KIND = "task";
    public static final String TAG_DELIMITER = " @";
    public static final String DEFAULT_DATE_LANG = "en";
    public static final String DEFAULT_DURATION_UNIT = "None";
    public static final String TIMEZONE_ENV = "TZ";
    public static final String DEFAULT_TIMEZONE = "Europe/Stockholm";
    public static final String DEFAULT_INDENT = "1";

}
