package com.ecommerce.WatchStore.Configuration;

import com.ecommerce.WatchStore.Component.JwtTokenFilter;
import com.ecommerce.WatchStore.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers(
                                    String.format("%s/user/register", apiPrefix),
                                    String.format("%s/user/login", apiPrefix)
                            )
                            .permitAll()
                            .requestMatchers(HttpMethod.POST, String.format("%s/category/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT, String.format("%s/category/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/category/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.GET, String.format("%s/category/**", apiPrefix)).permitAll()

                            .requestMatchers(HttpMethod.POST, String.format("%s/comment/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.PUT, String.format("%s/comment/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/comment/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.GET, String.format("%s/comment/**", apiPrefix)).permitAll()

                            .requestMatchers(HttpMethod.POST, String.format("%s/replyComment/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.PUT, String.format("%s/replyComment/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/replyComment/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.GET, String.format("%s/replyComment/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)

                            .requestMatchers(HttpMethod.POST, String.format("%s/order/**", apiPrefix)).hasRole(Roles.MEMBER)
                            .requestMatchers(HttpMethod.PUT, String.format("%s/order/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/order/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.GET, String.format("%s/order/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)

                            .requestMatchers(HttpMethod.POST, String.format("%s/orderDetail/**", apiPrefix)).hasRole(Roles.MEMBER)
                            .requestMatchers(HttpMethod.PUT, String.format("%s/orderDetail/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/orderDetail/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)
                            .requestMatchers(HttpMethod.GET, String.format("%s/orderDetail**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)

                            .requestMatchers(HttpMethod.POST, String.format("%s/brand/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT, String.format("%s/brand/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/brand/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.GET, String.format("%s/brand**", apiPrefix)).permitAll()

                            .requestMatchers(HttpMethod.POST, String.format("%s/watches/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT, String.format("%s/watches/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/watches/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.GET, String.format("%s/watches/**", apiPrefix)).permitAll()


                            .requestMatchers(HttpMethod.GET, String.format("%s/user/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.MEMBER)

                            .anyRequest().authenticated();
                });

        return http.build();
    }
}
