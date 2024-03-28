//package com.salessytem.usermicroservice.service.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ValidatePasswordBCryptImpl implements ValidatePassword {
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public boolean validatePassword(User user, String oldPassword) {
//        String userPassword = user.getPassword();
//        return (passwordEncoder.matches(oldPassword, userPassword));
//    }
//}
