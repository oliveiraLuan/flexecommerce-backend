package com.flexautopecas.flexecommerce.repositories;

import com.flexautopecas.flexecommerce.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
