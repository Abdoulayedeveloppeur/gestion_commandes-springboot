package com.example.gestioncommandes.service;

import com.example.gestioncommandes.dto.ClientDTO;
import java.util.List;

public interface ClientService {
    ClientDTO creer(ClientDTO dto);
    ClientDTO modifier(Long id, ClientDTO dto);
    void supprimer(Long id);
    ClientDTO rechercher(Long id);
    List<ClientDTO> liste();
}
