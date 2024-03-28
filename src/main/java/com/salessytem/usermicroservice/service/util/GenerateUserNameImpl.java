package com.salessytem.usermicroservice.service.util;

import com.salessytem.usermicroservice.domain.User;
import com.salessytem.usermicroservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("manualUserName")
@Slf4j
public class GenerateUserNameImpl implements GenerateUserName {

    @Autowired
    private UserService userService;
    @Cacheable("usernameCounts")
    public Map<String, Integer> getUsernameCounts() {
        try {
            List<String> allUserNames = userService.getAllUserNames();

            if (allUserNames == null || allUserNames.isEmpty()) {
                return Collections.emptyMap();
            }

            return allUserNames.stream()
                    .collect(Collectors.groupingBy(
                            key -> key,
                            Collectors.summingInt(e -> 1)
                    ));
        } catch (Exception e) {
            log.error("Error retrieving username counts", e);
            throw new RuntimeException("Error retrieving username counts", e);
        }
    }


    @Override
    public String generateUsername(String firstName, String lastName) {

        try{
            Map<String, Integer> usernameCounts = getUsernameCounts();

            String baseUsername = (firstName + "." + lastName).toLowerCase().replace(" ","").trim();
            String username = baseUsername;

            if (usernameCounts != null) {
                int count = 1;
                while (usernameCounts.containsKey(username)) {
                    count++;
                    username = baseUsername + count;
                }
            }
            log.info("Generated username");
            return username;
        } catch(Exception e){
            log.error("Error generating username for {} {}", firstName, lastName, e);
            throw new RuntimeException("Error generating username", e);
        }
    }

}
