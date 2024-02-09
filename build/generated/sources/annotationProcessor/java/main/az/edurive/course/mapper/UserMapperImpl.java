package az.edurive.course.mapper;

import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.UserResponse;
import az.edurive.course.model.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T00:14:23+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User requestToEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( userRequest.getName() );
        user.setSurname( userRequest.getSurname() );
        user.setGmail( userRequest.getGmail() );
        user.setPassword( userRequest.getPassword() );
        user.setPicture( userRequest.getPicture() );
        user.setRoleType( userRequest.getRoleType() );

        return user;
    }

    @Override
    public UserResponse entityToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setSurname( user.getSurname() );
        userResponse.setGmail( user.getGmail() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setPicture( user.getPicture() );
        userResponse.setRoleType( user.getRoleType() );

        return userResponse;
    }
}
