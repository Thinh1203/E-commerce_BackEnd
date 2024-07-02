package com.ecommerce.WatchStore.ResponseData;

import com.ecommerce.WatchStore.Model.Role;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor

public class UserDetailResponse {
    private Long id;
    private String email;
    private String numberPhone;
    private String firstName;
    private String lastName;
    private String gender;
    private int facebookAccountId;
    private int googleAccountId;
    private Date birthday;
    private Role role;
    private boolean enabled;
    private String username;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
}
