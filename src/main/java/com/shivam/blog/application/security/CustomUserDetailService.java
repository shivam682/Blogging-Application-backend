package com.shivam.blog.application.security;

import com.shivam.blog.application.exceptions.ResourceNotFoundException;
import com.shivam.blog.application.models.User;
import com.shivam.blog.application.repositries.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","Email"+username,0));
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).roles(String.valueOf(user.getRoles())).build();
        return userDetails;
    }

}
