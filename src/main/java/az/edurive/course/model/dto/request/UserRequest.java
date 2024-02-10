package az.edurive.course.model.dto.request;

import az.edurive.course.model.enums.RoleType;
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
    @NotBlank(message = "Name cannot be empty")
    String name;
    @NotBlank(message = "Surname cannot be empty")
    String surname;
    @NotNull
    @Email(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "Invalid email address")
    String gmail;
    @Size(min = 8)
    @NotBlank(message = "Password cannot be empty")
    String password;
    String picture;
    @NotNull
    RoleType roleType;

    //google ile giris ve forgot passsword maile gonderilmelidr orda passwordu deyismelidr
    //datalar cekmek butun suallar ve dogru suallar

}
