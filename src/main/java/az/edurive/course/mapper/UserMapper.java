package az.edurive.course.mapper;

import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.UserResponse;
import az.edurive.course.model.entity.User;
import org.mapstruct.Mapper;

/**
 * @author ashraf on 31.01.24
 * @project course
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(UserRequest userRequest);

    UserResponse entityToResponse(User user);


}
