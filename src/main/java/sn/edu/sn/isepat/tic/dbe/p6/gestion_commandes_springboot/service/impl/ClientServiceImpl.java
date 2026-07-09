package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.ClientDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.ClientService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClientServiceImpl implements ClientService {

    private final Map<Long, ClientDTO> clients = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public List<ClientDTO> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        ClientDTO client = clients.get(id);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client introuvable");
        }
        return client;
    }

    @Override
    public ClientDTO createClient(ClientDTO dto) {
        ClientDTO client = new ClientDTO();
        client.setId(sequence.getAndIncrement());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        clients.put(client.getId(), client);
        return client;
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        ClientDTO existing = clients.get(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client introuvable");
        }
        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());
        return existing;
    }

    @Override
    public void deleteClient(Long id) {
        if (!clients.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client introuvable");
        }
        clients.remove(id);
    }
}
