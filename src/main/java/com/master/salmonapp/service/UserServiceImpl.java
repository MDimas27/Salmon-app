package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.entity.Role;
import com.master.salmonapp.entity.Role.RoleName;
import com.master.salmonapp.entity.User;
import com.master.salmonapp.model.UserModel;
import com.master.salmonapp.model.UserRequestModel;
import com.master.salmonapp.repository.RoleRepository;
import com.master.salmonapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
			grantedAuthorities.add(grantedAuthority);
		});
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserModel register(UserRequestModel requestModel) {
		User userByUsername = userRepository.findByUsername(requestModel.getUsername());
		if (userByUsername != null && userByUsername.getId() != null)
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Username already exists");
		User userByEmail= userRepository.findByUsername(requestModel.getEmail());
		if (userByEmail != null && userByEmail.getId() != null)
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Email already exists");
		
		Role role = roleRepository.findByRoleName(RoleName.ROLE_USER.toString());
		User newUser = new User();
		// newUser.setAddress(requestModel.getAddress());
		newUser.setEmail(requestModel.getEmail());
		newUser.setFullName(requestModel.getFullName());
		newUser.setPassword(passwordEncoder.encode(requestModel.getPassword()));
		newUser.setPhone(requestModel.getPhone());
		newUser.setRoles(Collections.singleton(role));
		newUser.setStatus(Status.ACTIVE);
		newUser.setUsername(requestModel.getUsername());
		
		newUser = userRepository.save(newUser);
		
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(newUser, userModel);
		return userModel;
	}
}
