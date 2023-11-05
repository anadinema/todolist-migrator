package dev.anadinema.projects.migration.exception.handler;

import dev.anadinema.projects.migration.exception.InvalidRequestException;
import dev.anadinema.projects.migration.model.ErrorObject;
import dev.anadinema.projects.migration.model.ErrorResponse;
import dev.anadinema.projects.migration.util.LogUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Log4j2
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorObject errorObject = new ErrorObject();

        errorResponse.setTimestamp(OffsetDateTime.now());
        errorObject.setError(exception.getParameterName());
        errorObject.setDetail(exception.getMessage());
        errorResponse.addErrorsItem(errorObject);

        log.error(LogUtils.EXCEPTION_HANDLER_LOG_MSG,
                exception.getMessage(), Arrays.stream(exception.getStackTrace()).toList().get(0).getMethodName());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorObject errorObject = new ErrorObject();

        errorResponse.setTimestamp(OffsetDateTime.now());
        errorObject.setError(exception.getMessage());
        errorObject.setDetail(LogUtils.ExceptionAndKeys.INVALID_REQ_EXP_HANDLER_RESPONSE);
        errorResponse.addErrorsItem(errorObject);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimestamp(OffsetDateTime.now());
        errorResponse.errors(exception.getBindingResult().getAllErrors()
                .stream().map(objectError -> {
                    ErrorObject errorObject = new ErrorObject();
                    errorObject.setError(((FieldError) objectError).getField());
                    errorObject.setDetail(objectError.getDefaultMessage());
                    return errorObject;
                }).distinct().toList());

        log.error(LogUtils.EXCEPTION_HANDLER_LOG_MSG,
                exception.getMessage(), Arrays.stream(exception.getStackTrace()).toList().get(0).getMethodName());

        return new ResponseEntity<>(errorResponse, exception.getStatusCode());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> invalidRequestException(InvalidRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorObject errorObject = new ErrorObject();

        errorResponse.setTimestamp(OffsetDateTime.now());
        errorObject.setError(LogUtils.ExceptionAndKeys.INVALID_REQ_EXP_HANDLER_RESPONSE);
        errorObject.setDetail(exception.getMessage());
        errorResponse.addErrorsItem(errorObject);

        log.error(LogUtils.EXCEPTION_HANDLER_LOG_MSG,
                exception.getMessage(), Arrays.stream(exception.getStackTrace()).toList().get(0).getMethodName());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
