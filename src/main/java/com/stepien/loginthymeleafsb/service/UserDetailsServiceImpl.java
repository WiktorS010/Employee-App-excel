package com.stepien.loginthymeleafsb.service;

import com.stepien.loginthymeleafsb.model.CustomUserDetails;
import com.stepien.loginthymeleafsb.model.UserDtls;
import com.stepien.loginthymeleafsb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDtls userDtls = userRepository.findByEmail(email);
        if (userDtls != null) {
            return new CustomUserDetails(userDtls);
        }
        throw new UsernameNotFoundException("user not avaible");
    }

}
