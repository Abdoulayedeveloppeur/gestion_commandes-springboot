package com.example.gestioncommandes.service.impl;

import com.example.gestioncommandes.dto.ClientDTO;
import com.example.gestioncommandes.entity.Client;
import com.example.gestioncommandes.repository.ClientRepository;
import com.example.gestioncommandes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDTO creer(ClientDTO dto) {
        Client client = new Client(dto.getNom(), dto.getEmail());
        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getNom(), client.getEmail());
    }

    @Override
    public ClientDTO modifier(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getNom(), client.getEmail());
    }

    @Override
    public void supprimer(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO rechercher(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        return new ClientDTO(client.getId(), client.getNom(), client.getEmail());
    }

    @Override
    public List<ClientDTO> liste() {
        return clientRepository.findAll()
                .stream()
                .map(c -> new ClientDTO(c.getId(), c.getNom(), c.getEmail()))
                .collect(Collectors.toList());
    }
}
