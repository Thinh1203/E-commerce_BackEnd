package com.ecommerce.WatchStore.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "avatar")
//    private String avatar;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "numberPhone", nullable = false)
    private String numberPhone;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "facebookAccountId", nullable = true)
    private Integer facebookAccountId;

    @Column(name = "googleAccountId", nullable = true)
    private Integer googleAccountId;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @PrePersist
    protected void onCreate() {
        if (facebookAccountId == null) {
            facebookAccountId = 0;
        }
        if (googleAccountId == null) {
            googleAccountId = 0;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + getRole().getName()));
        return authorityList;
    }

    // truong dung de dang nhap
    @Override
    public String getUsername() {
        return numberPhone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
