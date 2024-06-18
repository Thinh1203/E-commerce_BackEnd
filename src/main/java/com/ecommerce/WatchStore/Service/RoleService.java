package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.Role;
import com.ecommerce.WatchStore.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    Role addRole(RoleDTO roleDTO);

    List<Role> getAllRole();

    Role updateRole(long id, RoleDTO roleDTO);

    Role getRoleById(long id);

    void deleteRole(long id);
}
