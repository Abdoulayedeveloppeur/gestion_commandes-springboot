package com.example.gestioncommandes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gestioncommandes.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
