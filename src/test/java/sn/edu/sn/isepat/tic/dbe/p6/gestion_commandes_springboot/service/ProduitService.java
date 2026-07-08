package com.example.gestioncommandes.service;

import com.example.gestioncommandes.dto.ProduitDTO;
import java.util.List;

public interface ProduitService {
    ProduitDTO creer(ProduitDTO dto);
    ProduitDTO modifier(Long id, ProduitDTO dto);
    void supprimer(Long id);
    ProduitDTO rechercher(Long id);
    List<ProduitDTO> liste();
}
