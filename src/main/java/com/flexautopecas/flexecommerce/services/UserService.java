package com.flexautopecas.flexecommerce.services;

import com.flexautopecas.flexecommerce.dto.RoleDTO;
import com.flexautopecas.flexecommerce.dto.UserDTO;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(user -> new UserDTO(user));
    }

    @Transactional
    public UserDTO insert(UserDTO dto){
        User user = new User();
        User entity = copyDTOtoEntity(user, dto);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    public User copyDTOtoEntity(User entity, UserDTO dto){
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
}
