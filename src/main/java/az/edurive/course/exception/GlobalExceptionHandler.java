package az.edurive.course.exception;

import az.edurive.course.model.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author ashraf on 31.01.24
 * @project course
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({NotFoundException.class,AlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorCode(errorResponse.getErrorCode());
        errorResponse.setStatus(getHttpStatus(exception));
        errorResponse.setDate(LocalDateTime.now());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
    private HttpStatus getHttpStatus(Exception ex){
        if(ex instanceof NotFoundException){
            return HttpStatus.NOT_FOUND;
        }else if (ex instanceof AlreadyExistsException){
            return HttpStatus.CONFLICT;
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
