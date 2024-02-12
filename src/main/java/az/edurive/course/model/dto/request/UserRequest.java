package az.edurive.course.model.dto.request;

import az.edurive.course.model.enums.RoleType;
import jakarta.validation.constraints.*;
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
    @NotNull
    RoleType roleType;
//    @NotNull
//    @Pattern(regexp = "^(\\d{2}( \\d{3}){2} \\d{2})$|^\\d{9}$", message = "Invalid phone number format. Use 'XX XXX XX XX' or 'XXXXXXXXX' format.")
//    String phoneNumber;

    //google ile giris ve forgot passsword maile gonderilmelidr orda passwordu deyismelidr
    //datalar cekmek butun suallar ve dogru suallar

}
