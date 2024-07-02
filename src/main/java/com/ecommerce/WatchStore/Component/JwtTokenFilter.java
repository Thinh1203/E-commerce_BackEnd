package com.ecommerce.WatchStore.Component;

import com.ecommerce.WatchStore.Model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${api.prefix}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtToken jwtToken;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
       try {
           if (isBypassToken(request)) {
               filterChain.doFilter(request, response);
               return;
           }
           final String authHeader = request.getHeader("Authorization");
           if (authHeader == null || !authHeader.startsWith("Bearer ")) {
               response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
               return;
           }
           final String token = authHeader.substring(7);
           final String numberPhone = jwtToken.extractNumberPhone(token);
           if (numberPhone != null && SecurityContextHolder.getContext().getAuthentication() == null)  {
               User userDetails = (User) userDetailsService.loadUserByUsername(numberPhone);
               if (jwtToken.validateToken(token, userDetails)) {
                   UsernamePasswordAuthenticationToken authenticationToken =
                           new UsernamePasswordAuthenticationToken(
                                   userDetails,
                                   null,
                                   userDetails.getAuthorities()
                           );
                   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               }
           }
           filterChain.doFilter(request, response);
       } catch (Exception e) {
           logger.error("JWT validation error: ", e);
           response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
       }
    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of(String.format("%s/category", apiPrefix), "GET"),
                Pair.of(String.format("%s/comment", apiPrefix), "GET"),
                Pair.of(String.format("%s/user/register", apiPrefix), "POST"),
                Pair.of(String.format("%s/user/login", apiPrefix), "POST"),
                Pair.of(String.format("%s/brand", apiPrefix), "GET"),
                Pair.of(String.format("%s/watches", apiPrefix), "GET")
        );
        for (Pair<String, String> bypassToken : bypassTokens) {
            if (request.getServletPath().contains(bypassToken.getFirst())
                    && request.getMethod().equals(bypassToken.getSecond())
            ) {
                return true;
            }
        }
        return false;
    }
}
