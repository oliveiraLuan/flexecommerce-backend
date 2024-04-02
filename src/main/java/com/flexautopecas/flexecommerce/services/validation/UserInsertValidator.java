package com.flexautopecas.flexecommerce.services.validation;

import com.flexautopecas.flexecommerce.dto.UserInsertDTO;
import com.flexautopecas.flexecommerce.entities.User;
import com.flexautopecas.flexecommerce.repositories.UserRepository;
import com.flexautopecas.flexecommerce.resources.exceptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid ann){
    }
    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail(dto.getEmail());

        if(user != null){
            list.add(new FieldMessage("email", "Email já existe"));
        }

        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
