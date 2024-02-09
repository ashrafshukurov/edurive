package az.edurive.course.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author ashraf on 31.01.24
 * @demo
 */

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class AlreadyExistsException extends RuntimeException{

    private final HttpStatus httpStatus=HttpStatus.CONFLICT;
    private final String message;


}
