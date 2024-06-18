package com.ecommerce.WatchStore.Controller;


import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Model.Role;
import com.ecommerce.WatchStore.Service.RoleService;
import com.ecommerce.WatchStore.dto.RoleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("")
    public ResponseData<?> getAllRole() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "List role: ", roleService.getAllRole());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseData<?> addRole(@Valid @RequestBody RoleDTO roleDTO) {
        try {
            Role newRole = roleService.addRole(roleDTO);
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Added role successfully!",
                    newRole
            );
        } catch (DataIntegrityViolationException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Role deleted successfully!");
        } catch (IllegalArgumentException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateRole(@Valid @RequestBody RoleDTO roleDTO, @PathVariable Long id) {
        try {
            Role updateRole = roleService.updateRole(id, roleDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Role updated successfully!", updateRole);
        } catch (IllegalArgumentException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
