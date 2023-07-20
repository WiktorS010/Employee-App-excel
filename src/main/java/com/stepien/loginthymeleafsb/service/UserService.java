package com.stepien.loginthymeleafsb.service;

import com.stepien.loginthymeleafsb.model.UserDtls;

public interface UserService {

    void createUser(UserDtls userDtls);

    boolean checkEmail(String email);
}
