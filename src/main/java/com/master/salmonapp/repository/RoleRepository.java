package com.master.salmonapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.salmonapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByRoleName(String roleName);
}
