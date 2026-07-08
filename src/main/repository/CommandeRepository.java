package com.example.gestioncommandes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.gestioncommandes.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {}
