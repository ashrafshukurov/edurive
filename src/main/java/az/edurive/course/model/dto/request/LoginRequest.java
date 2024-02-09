package az.edurive.course.model.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginRequest {
    private String gmail;
    private String password;
}
