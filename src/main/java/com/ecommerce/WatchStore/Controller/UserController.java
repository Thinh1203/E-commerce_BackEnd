package com.ecommerce.WatchStore.Controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Model.User;
import com.ecommerce.WatchStore.ResponseData.UserDetailResponse;
import com.ecommerce.WatchStore.Service.UserService;
import com.ecommerce.WatchStore.dto.UserDTO;
import com.ecommerce.WatchStore.dto.UserLoginDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseData<?> getOneUser(@PathVariable Long id) {
        try {
            UserDetailResponse user = userService.getOneUser(id);
            return new ResponseData<>(HttpStatus.OK.value(), "User detail: ", user);
        } catch (IllegalArgumentException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseData<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            User newUser = userService.createUser(userDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Register successfully!", newUser);
        } catch (DataIntegrityViolationException e) {
            return new ResponseError(HttpStatus.CONFLICT.value(), e.getMessage());
        }  catch (EntityNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }  catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseData<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(userLoginDTO.getNumberPhone(), userLoginDTO.getPassword());
            return new ResponseData<>(HttpStatus.OK.value(), "Login successfully!", token);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
