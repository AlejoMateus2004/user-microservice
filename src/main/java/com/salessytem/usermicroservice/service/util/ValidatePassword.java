package com.salessytem.usermicroservice.service.util;

import com.salessytem.usermicroservice.domain.User;

public interface ValidatePassword {
    boolean validatePassword(User user, String oldPassword);
}
