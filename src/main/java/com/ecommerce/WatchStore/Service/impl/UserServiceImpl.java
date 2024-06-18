package com.ecommerce.WatchStore.Service.impl;

import com.ecommerce.WatchStore.Component.JwtToken;
import com.ecommerce.WatchStore.Model.Role;
import com.ecommerce.WatchStore.Model.User;
import com.ecommerce.WatchStore.Repository.RoleRepository;
import com.ecommerce.WatchStore.Repository.UserRepository;
import com.ecommerce.WatchStore.Service.UserService;
import com.ecommerce.WatchStore.dto.UserDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtToken jwtToken;
    private final AuthenticationManager authenticationManager;

    @Override
    public User createUser(UserDTO request){
        String checkNumberPhone = request.getNumberPhone();
        if (userRepository.existsByNumberPhone(checkNumberPhone)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        User newUser = User.builder()
                .firstName(request.getFistName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .numberPhone(request.getNumberPhone())
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .facebookAccountId(request.getFacebookAccountId())
                .googleAccountId(request.getGoogleAccountId())
                .password(request.getPassword())
                .build();
        newUser.setRole(role);
        if (request.getFacebookAccountId() == 0 && request.getGoogleAccountId() == 0) {
            String password = request.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String numberPhone, String password) throws Exception{
        Optional<User> user = userRepository.findByNumberPhone(numberPhone);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("Invalid numberPhone or password");
        }
        User existingUser = user.get();
        if (existingUser.getFacebookAccountId() == 0 && existingUser.getGoogleAccountId() == 0) {
            if (!passwordEncoder.matches(password, existingUser.getPassword())) {
                throw new BadCredentialsException("Wrong phone number or password");
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                numberPhone, password, existingUser.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtToken.generateToken(existingUser);
    }
}
