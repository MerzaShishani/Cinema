package com.merza.Cinema.service.Impls;


import com.merza.Cinema.entity.Role;
import com.merza.Cinema.exception.ElementNotFoundException;
import com.merza.Cinema.repository.RoleRepository;
import com.merza.Cinema.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id){
        return roleRepository.findById(id).orElseThrow(() ->new ElementNotFoundException(id));
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }
}

