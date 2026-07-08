package com.example.gestioncommandes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gestioncommandes.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}
