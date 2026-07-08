package com.example.gestioncommandes.service;

import com.example.gestioncommandes.dto.CommandeDTO;
import com.example.gestioncommandes.dto.LigneCommandeDTO;

public interface CommandeService {
    CommandeDTO creer(CommandeDTO dto);
    CommandeDTO ajouterLigne(Long commandeId, LigneCommandeDTO ligneDTO);
    CommandeDTO supprimerLigne(Long commandeId, Long ligneId);
    CommandeDTO modifier(Long id, CommandeDTO dto);
    void supprimer(Long id);
    CommandeDTO consulter(Long id);
}
