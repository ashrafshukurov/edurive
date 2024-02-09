package az.edurive.course.controller;

import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.UserResponse;
import az.edurive.course.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ashraf on 31.01.24
 * @project course
 */
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "adding user", notes = "add to user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 400, message = "Invalid insert")
    })
    @PostMapping("/")
    public void addUser(@Valid @RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }

    @ApiOperation(value = "Get-User-By-Id", notes = "When you enter id it will get User", response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting User by Id")
    })

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiOperation(value = "Getting-All-User", notes = "It Will return User list", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting User")
    })
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }


    @ApiOperation(value = "Update User", notes = "Update User based on Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid update")
    })
    @PutMapping("/{id}")
    public void updateUser(@Valid @RequestBody UserRequest userRequest,@PathVariable Long id){
        userService.updateUser(userRequest,id);
    }

    @ApiOperation(value = "Deleting User",notes = "Deleting user based on id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully work"),
            @ApiResponse(code=404,message = "Invalid deleting user by id")
    })
    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
