package com.salessytem.usermicroservice.service.facade;

import com.salessytem.usermicroservice.domain.Login.AuthenticationResponse;
import com.salessytem.usermicroservice.domain.User;
import com.salessytem.usermicroservice.domain.records.UserRecord.UserResponse;
import com.salessytem.usermicroservice.domain.records.UserRecord.UserRequest;
import com.salessytem.usermicroservice.mapper.UserMapper;
import com.salessytem.usermicroservice.service.UserService;
import com.salessytem.usermicroservice.service.util.GeneratePassword;
import com.salessytem.usermicroservice.service.util.GenerateUserName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceFacade {

    private UserService userService;

    private GeneratePassword generatePassword;

    private GenerateUserName generateUsername;

    private UserMapper userMapper;

    public ResponseEntity<AuthenticationResponse> saveUser( UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);
        String username = generateUsername.generateUsername(user.getFirstName(), user.getLastName());

        user.setUserName(username);
        user.setIsActive(true);

        String password = generatePassword.generatePassword();
        user.setPassword(password);

        User result = userService.saveUser(user);

        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        AuthenticationResponse response = new AuthenticationResponse();
        response.setFullName(user.getFirstName() + " " + user.getLastName());
        response.setUsername(user.getUserName());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<UserResponse> getUserByUserUsername(String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponse response = userMapper.userToUserResponse(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteUserByUsername(String username) {
        try {
            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            userService.deleteUser(user);
            return new ResponseEntity<>("Deleted user", HttpStatus.OK);
        }catch(Exception e){
            log.error("Error deleting User",e);
            return new ResponseEntity<>("User not deleted", HttpStatus.CONFLICT);
        }
    }
}
