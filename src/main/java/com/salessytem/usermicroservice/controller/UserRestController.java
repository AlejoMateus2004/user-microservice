package com.salessytem.usermicroservice.controller;

import com.salessytem.usermicroservice.domain.Login.AuthenticationResponse;
import com.salessytem.usermicroservice.domain.records.UserRecord.UserRequest;
import com.salessytem.usermicroservice.domain.records.UserRecord.UserResponse;
import com.salessytem.usermicroservice.service.facade.UserServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User Controller", value = "Operations for creating, updating, retrieving and deleting Users")
@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserServiceFacade userServiceFacade;

    @Autowired
    public UserRestController(UserServiceFacade userServiceFacade) {
        this.userServiceFacade = userServiceFacade;
    }

    @ApiOperation(value = "Save User", notes = "Register a new User in the system")
    @PostMapping("/public/save")
    public ResponseEntity<AuthenticationResponse> saveUser(@RequestBody @Validated UserRequest userRequest){
        return userServiceFacade.saveUser(userRequest);

    }

    @ApiOperation(value = "Get User", notes = "Retrieve an existing User")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username){
        return userServiceFacade.getUserByUserUsername(username);
    }

    @ApiOperation(value = "Delete User")
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){
        return userServiceFacade.deleteUserByUsername(username);
    }


}
