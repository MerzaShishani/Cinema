package com.merza.Cinema.service;

import com.merza.Cinema.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role findById(Long id);
    Role findByName(String name);
    Role save(Role entity);
}
