package com.proekt.dnick.aldehyde.configuration;


import com.proekt.dnick.aldehyde.model.Role;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class DataInitializer {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @PostConstruct
    public void initData() {
        User u = new User();
        u.setFirstName("admin");
        u.setAddress("admin");
        u.setLastName("admin");
        u.setCity("admin city?");
        u.setRoles(Collections.singleton(Role.ADMIN));
        u.setEmail("admin@proekt.com");
        u.setPassword(passwordEncoder.encode("admin"));
        u.setActive(true);
       userRepository.save(u);

    }
}
