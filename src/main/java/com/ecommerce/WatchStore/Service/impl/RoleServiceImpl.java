package com.ecommerce.WatchStore.Service.impl;

import com.ecommerce.WatchStore.Model.Role;
import com.ecommerce.WatchStore.Repository.RoleRepository;
import com.ecommerce.WatchStore.Service.RoleService;
import com.ecommerce.WatchStore.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role addRole(RoleDTO request) {
        if (roleRepository.existsByName(request.getName())) {
            throw new DataIntegrityViolationException("Role already exists");
        }
        Role newRole = Role.builder()
                .name(request.getName())
                .build();
        return roleRepository.save(newRole);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(long id, RoleDTO request) {
        Role existingRole = getRoleById(id);
        existingRole.setName(request.getName());
        return roleRepository.save(existingRole);
    }

    @Override
    public Role getRoleById(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

    @Override
    public void deleteRole(long id) {
        Role existingRole = getRoleById(id);
        roleRepository.delete(existingRole);
    }
}
