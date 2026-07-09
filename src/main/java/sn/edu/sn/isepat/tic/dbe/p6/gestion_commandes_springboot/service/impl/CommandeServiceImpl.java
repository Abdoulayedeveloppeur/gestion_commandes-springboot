package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.CommandeDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.LigneCommandeDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.CommandeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final Map<Long, CommandeDTO> commandes = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public List<CommandeDTO> getAll() {
        return new ArrayList<>(commandes.values());
    }

    @Override
    public CommandeDTO getById(Long id) {
        CommandeDTO commande = commandes.get(id);
        if (commande == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande introuvable");
        }
        return commande;
    }

    @Override
    public CommandeDTO create(CommandeDTO dto) {
        CommandeDTO commande = new CommandeDTO();
        commande.setId(sequence.getAndIncrement());
        commande.setStatus(dto.getStatus() != null ? dto.getStatus() : "EN_COURS");
        commande.setClientId(dto.getClientId());
        commandes.put(commande.getId(), commande);
        return commande;
    }

    @Override
    public CommandeDTO update(Long id, CommandeDTO dto) {
        CommandeDTO existing = commandes.get(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande introuvable");
        }
        existing.setStatus(dto.getStatus());
        existing.setClientId(dto.getClientId());
        return existing;
    }

    @Override
    public void delete(Long id) {
        if (!commandes.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande introuvable");
        }
        commandes.remove(id);
    }

    @Override
    public CommandeDTO valider(Long id) {
        CommandeDTO commande = getById(id);
        commande.setStatus("VALIDEE");
        return commande;
    }

    @Override
    public CommandeDTO ajouterLigne(Long id, LigneCommandeDTO dto) {
        return getById(id);
    }

    @Override
    public List<CommandeDTO> commandesClient(Long clientId) {
        return getAll().stream().filter(c -> clientId.equals(c.getClientId())).toList();
    }

    @Override
    public List<CommandeDTO> entreDates(String debut, String fin) {
        return getAll();
    }

    @Override
    public Double chiffreAffaires() {
        return 0.0;
    }

    @Override
    public Object totalParClient() {
        return new ArrayList<>();
    }
}
