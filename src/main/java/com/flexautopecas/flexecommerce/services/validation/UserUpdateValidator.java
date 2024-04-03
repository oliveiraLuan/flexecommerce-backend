package com.flexautopecas.flexecommerce.services.validation;

import com.flexautopecas.flexecommerce.dto.UserUpdateDTO;
import com.flexautopecas.flexecommerce.entities.User;
import com.flexautopecas.flexecommerce.repositories.UserRepository;
import com.flexautopecas.flexecommerce.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        var uriVars = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long userId = Long.parseLong(uriVars.get("id"));

        User user = userRepository.findByEmail(dto.getEmail());

        if(user != null && userId == dto.getId()){
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        for(FieldMessage fieldMessage : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getMessage())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
