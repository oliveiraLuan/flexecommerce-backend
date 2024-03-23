package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.dto.UserDTO;
import com.flexautopecas.flexecommerce.entities.User;
import com.flexautopecas.flexecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(user -> new UserDTO(user, user.getRoles()));
    }
}
