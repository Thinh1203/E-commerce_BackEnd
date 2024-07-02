package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.User;
import com.ecommerce.WatchStore.ResponseData.UserDetailResponse;
import com.ecommerce.WatchStore.dto.UserDTO;

public interface UserService {
    User createUser(UserDTO userDTO);

    String login(String numberPhone, String password) throws Exception;

    UserDetailResponse getOneUser(long id);
}
