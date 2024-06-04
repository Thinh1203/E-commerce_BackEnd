package com.ecommerce.WatchStore.Controller;


import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.RoleDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/role")
public class RoleController {

    @GetMapping("")
    public ResponseData<?> getAllRole() {
        return new ResponseData<>(HttpStatus.OK.value(), "Successfully", "data");
    }

    @PostMapping("")
    public ResponseData<?> addRole( @Valid @RequestBody RoleDTO roleDTO) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Reply comment added successfully! ",
                String.format(
                        "Reply comment  with id = %s, content = %s",
                        roleDTO.getRole(),
                        roleDTO.getDescription()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteRole(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Role deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateRole(@Valid @RequestBody RoleDTO roleDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(),
                "Role updated successfully!",
                String.format("Update comment with id = %d, content = %s", id, roleDTO.getRole())
        );
    }

}
