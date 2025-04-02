package com.edstry.Tasklist.web.security;

// Сервис, вызывается спрингом для авторизации пользователей

import com.edstry.Tasklist.domain.user.User;
import com.edstry.Tasklist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
    private final UserService userService;

    // Security при авторизации пользователя будет использовать этот метод
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        return JwtEntityFactory.create(user);
    }
}
