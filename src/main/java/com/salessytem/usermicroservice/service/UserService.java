package com.salessytem.usermicroservice.service;

import com.salessytem.usermicroservice.dao.UserRepository;
import com.salessytem.usermicroservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private  UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Transactional
    public boolean deleteUser(User user){
        try {
            userRepository.delete(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return (User) userRepository.findByUserName(username).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<String> getAllUserNames() {
        return userRepository.getAllUsernames();
    }

}
