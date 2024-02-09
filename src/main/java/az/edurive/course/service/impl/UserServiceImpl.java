package az.edurive.course.service.impl;

import az.edurive.course.exception.AlreadyExistsException;
import az.edurive.course.exception.NotFoundException;
import az.edurive.course.mapper.UserMapper;
import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.UserResponse;
import az.edurive.course.model.entity.User;
import az.edurive.course.repository.UserRepository;
import az.edurive.course.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ashraf on 31.01.24
 * @project course
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserRequest userRequest) {
       User user=userMapper.requestToEntity(userRequest);
       if(userRepository.existsByGmail(user.getGmail())){
           throw new AlreadyExistsException("There is a user with this gmail:"+user.getGmail());
       }
       userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> list = userRepository.findAll();
        return list.stream().map(userMapper::entityToResponse).toList();
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no user with this id=" + id));
        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no user with this id=" + id));
        return userMapper.entityToResponse(user);
    }

    @Override
    public void updateUser(UserRequest userRequest, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no user with this id=" + id));
        User entityUser=userMapper.requestToEntity(userRequest);
        entityUser.setId(user.getId());
        userRepository.save(entityUser);

    }
}
