package com.example.gestioncommandes.service;

import com.example.gestioncommandes.dto.UtilisateurDTO;
import java.util.List;

public interface UtilisateurService {
    UtilisateurDTO creer(UtilisateurDTO dto);
    UtilisateurDTO modifier(Long id, UtilisateurDTO dto);
    void supprimer(Long id);
    UtilisateurDTO rechercher(Long id);
    List<UtilisateurDTO> liste();
}
