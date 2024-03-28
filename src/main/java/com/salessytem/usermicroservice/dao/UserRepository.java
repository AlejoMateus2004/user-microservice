package com.salessytem.usermicroservice.dao;

import com.salessytem.usermicroservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByUserName(String username);

    @Query("SELECT u.userName FROM User u")
    List<String> getAllUsernames();
}
