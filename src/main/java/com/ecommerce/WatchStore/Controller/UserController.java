package com.ecommerce.WatchStore.Controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.dto.UserDTO;
import com.ecommerce.WatchStore.dto.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/user")
public class UserController {

    @PostMapping("/register")
    public ResponseData<?> creatUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Register successfully!", userDTO.getNumberPhone());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "error");
        }
    }

    @PostMapping("/login")
    public ResponseData<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseData<>(HttpStatus.OK.value(), "Login successfully!");
    }
}
