package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service;

import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long id);
    ClientDTO createClient(ClientDTO dto);
    ClientDTO updateClient(Long id, ClientDTO dto);
    void deleteClient(Long id);
}
