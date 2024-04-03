package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.dto.RoleDTO;
import com.flexautopecas.flexecommerce.dto.UserDTO;
import com.flexautopecas.flexecommerce.dto.UserInsertDTO;
import com.flexautopecas.flexecommerce.dto.UserUpdateDTO;
import com.flexautopecas.flexecommerce.entities.Role;
import com.flexautopecas.flexecommerce.entities.User;
import com.flexautopecas.flexecommerce.repositories.RoleRepository;
import com.flexautopecas.flexecommerce.repositories.UserRepository;
import com.flexautopecas.flexecommerce.services.exceptions.DatabaseException;
import com.flexautopecas.flexecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(user -> new UserDTO(user));
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto){
        User entity = new User();
        entity = copyDTOtoEntity(entity, dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Usuário com id informado não encontrado.");
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro de integridade referencial");
        }
    }

    @Transactional
    public UserDTO update(UserUpdateDTO dto, Long id) {
        User entity = new User();
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Usuário com id informado não encontrado.");
        }
        try{
            entity = copyDTOtoEntity(entity, dto);
            repository.save(entity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro de integridade referencial");
        }
        return new UserDTO(entity);
    }

    public User copyDTOtoEntity(User entity, UserDTO dto){
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for(RoleDTO roleDTO : dto.getRoles()){
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            entity.getRoles().add(role);
        }
        return entity;
    }
}
