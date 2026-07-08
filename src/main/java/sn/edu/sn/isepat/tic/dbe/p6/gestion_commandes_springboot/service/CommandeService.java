package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service;

import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.CommandeDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.LigneCommandeDTO;

import java.util.List;

public interface CommandeService {
    List<CommandeDTO> getAll();
    CommandeDTO getById(Long id);
    CommandeDTO create(CommandeDTO dto);
    CommandeDTO update(Long id, CommandeDTO dto);
    void delete(Long id);
    CommandeDTO valider(Long id);
    CommandeDTO ajouterLigne(Long id, LigneCommandeDTO dto);
    List<CommandeDTO> commandesClient(Long clientId);
    List<CommandeDTO> entreDates(String debut, String fin);
    Double chiffreAffaires();
    Object totalParClient();
}
