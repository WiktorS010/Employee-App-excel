package com.stepien.loginthymeleafsb.service;

import com.stepien.loginthymeleafsb.model.UserDtls;
import com.stepien.loginthymeleafsb.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void createUser(UserDtls userDtls) {

        userDtls.setPassword(bCryptPasswordEncoder.encode(userDtls.getPassword()));
        userDtls.setRole("ROLE_USER");
        userRepository.save(userDtls);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
