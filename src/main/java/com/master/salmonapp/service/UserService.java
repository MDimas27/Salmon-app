package com.master.salmonapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.master.salmonapp.model.UserModel;
import com.master.salmonapp.model.UserRequestModel;

public interface UserService extends UserDetailsService {

    UserModel register(UserRequestModel requestModel);

}