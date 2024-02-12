package az.edurive.course.model.dto.response;

import az.edurive.course.model.enums.RoleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String name;
    String surname;
    String gmail;
    String password;
    RoleType roleType;
//    String phoneNumber;


}
