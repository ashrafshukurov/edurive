package az.edurive.course.controller;

import az.edurive.course.model.dto.request.UserRequest;
import az.edurive.course.model.dto.response.UserResponse;
import az.edurive.course.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ashraf on 31.01.24
 * @project course
 */
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Tag(name = "User",description = "User Apis")
public class UserController {
    private final UserService userService;
    @Operation(
            summary = "Adding user",
            description = "Adding user for UserRequest",
            tags = {"User","Post"}
    )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = {@Content(schema = @Schema(implementation = UserRequest.class),mediaType = "app/json")}),
            @ApiResponse(responseCode = "400", content ={ @Content(schema = @Schema())})
    })

    @PostMapping("/")
    public void addUser(@Valid @RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }
    @Operation(
            summary = "Retrieve a User by Id",
            description = "Get a User object by specifying its id. The response is User object with id",
            tags = { "User", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(
            summary = "Retrieve a Users",
            description = "Get  Users object ",
            tags = { "Users", "getAll" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @Operation(
            summary = "Update a User by Id",
            description = "Update a User object by specifying its id. The response is User object with updated version",
            tags = { "User", "update" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public void updateUser(@Valid @RequestBody UserRequest userRequest,@PathVariable Long id){
        userService.updateUser(userRequest,id);
    }

    @Operation(
            summary = "Delete a User by Id",
            description = "Delete a User object by specifying its id",
            tags = { "user", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
