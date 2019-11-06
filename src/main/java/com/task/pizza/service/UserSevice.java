package com.task.pizza.service;

import com.task.pizza.domain.User;
import com.task.pizza.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSevice implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> getUsers() {

        ArrayList<User> users = new ArrayList<>();

        Iterable<User> pizzaIterable = userRepo.findAll();
        pizzaIterable.forEach(users::add);
        return users;
    }
}
