package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

// tag annotation from swagger, provides information for user resource
@Tag(
        name= "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs = Create User, Update User, Get User, Get All Users, Delete User"

)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    /* BUILD CREATE USER REST API*/

    // operation annotation from swagger provides summary attribute
    @Operation (
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    //ApiResponse annotation provide information about the rest ApiResponse
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /* BUILD GET USER BY ID REST API*/

    // operation annotation from swagger provides summary attribute
    @Operation (
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get a single user from the database"
    )
    //ApiResponse annotation provide information about the rest ApiResponse
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}") //http://localhost:8080/api/users/{id}
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /* BUILD GET ALL USER REST API*/

    // operation annotation from swagger provides summary attribute
    @Operation (
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get all the users from the database"
    )
    //ApiResponse annotation provide information about the rest ApiResponse
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping //http://localhost:8080/api/users
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /* BUILD UPDATE USER REST API */

    // operation annotation from swagger provides summary attribute
    @Operation (
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user from the database"
    )
    //ApiResponse annotation provide information about the rest ApiResponse
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}") // http://localhost:8080/api/users/{id} //incoming requests
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /* BUILD DELETE USER REST API */

    // operation annotation from swagger provides summary attribute
    @Operation (
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    //ApiResponse annotation provide information about the rest ApiResponse
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}") //http://localhost:8080/api/users/{id}
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
    /* MOVED TO GlobalExceptionHandler class */
//    annotation used to handle the specific exceptions and sending the custom response to the client
//    @ExceptionHandler(ResourceNotFoundException.class)
//    handle specific exceptions with respect to the controller
//        public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
//            ErrorDetails errorDetails = new ErrorDetails(
//                    LocalDateTime.now(),
//                    exception.getMessage(),
//                    webRequest.getDescription(false),
//                    "USER_NOT_FOUND"
//            );
//            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
