package az.edurive.course.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author ashraf on 31.01.24
 * @demo
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    HttpStatus status;
    LocalDateTime date;
    String errorMessage;
    int errorCode;
}
