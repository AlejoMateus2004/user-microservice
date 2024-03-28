package com.salessytem.usermicroservice.mapper;

import com.salessytem.usermicroservice.domain.User;
import com.salessytem.usermicroservice.domain.records.UserRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRequestToUser(UserRecord.UserRequest userRequest);
    UserRecord.UserResponse userToUserResponse(User user);
}
