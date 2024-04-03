package com.flexautopecas.flexecommerce.dto;

import com.flexautopecas.flexecommerce.services.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO{
    private String password;

    public UserInsertDTO(){

    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
