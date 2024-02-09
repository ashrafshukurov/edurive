package az.edurive.course.model.dto.request;

import az.edurive.course.model.enums.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @ApiModelProperty(notes = "name", example = "james", required = true)
    @NotBlank(message = "Name cannot be empty")
    String name;
    @ApiModelProperty(notes = "Surname", example = "Brown", required = true)
    @NotBlank(message = "Surname cannot be empty")
    String surname;
    @ApiModelProperty(notes = "gmail", example = "example@gmail.com", required = true)
    @NotNull
    @Email(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "Invalid email address")
    String gmail;
    @ApiModelProperty(notes = "Password", example = "123123123", required = true)
    @Size(min = 8)
    @NotBlank(message = "Password cannot be empty")
    String password;
    @ApiModelProperty(notes = "picture", example = "2387geg3782.jpg")
    String picture;
    @NotNull
    @ApiModelProperty(notes = "role_type", example = "Guest")
    RoleType roleType;

}
