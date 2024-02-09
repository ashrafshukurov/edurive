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
public class NotFoundException extends RuntimeException{
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private final String message;

}
