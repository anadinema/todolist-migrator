package dev.anadinema.projects.migration.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogUtils {

    public static final String EXCEPTION_HANDLER_LOG_MSG = "Exception : {}, in method : {}";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ExceptionAndKeys {
        public static final String EXCEPTION_KEY = "exception";
        public static final String INVALID_REQ_EXP_HANDLER_RESPONSE = "Invalid request body/parameter have failed the operation";

    }

}
