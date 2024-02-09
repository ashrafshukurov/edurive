package az.edurive.course.service;

import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ashraf on 31.01.24
 * @project course
 */
public interface UserService {
    void createUser(UserRequest userRequest);
    List<UserResponse> getAllUser();
    void deleteUserById(Long id);
    UserResponse getUserById(Long id);
    void updateUser(UserRequest userRequest,Long id);


}
