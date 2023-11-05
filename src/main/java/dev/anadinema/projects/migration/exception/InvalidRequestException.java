package dev.anadinema.projects.migration.exception;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.Serial;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidRequestException(String message) {
        super(message);
    }

}
