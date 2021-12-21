package com.example.newyear.security;

import com.example.newyear.DAO.UserRepository;
import com.example.newyear.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getName())
                .password(user.getPassword())
                .authorities(user.getRole()).build();
        return userDetails;
    }

}
