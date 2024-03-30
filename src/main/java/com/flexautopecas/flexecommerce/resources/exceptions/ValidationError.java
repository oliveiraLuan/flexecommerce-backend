package com.flexautopecas.flexecommerce.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }
    public void addError(FieldMessage error){
        errors.add(error);
    }
}
