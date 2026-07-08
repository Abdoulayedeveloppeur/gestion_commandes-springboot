package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.controller;

import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.ClientDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Lister les clients")
    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Client par ID")
    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @Operation(summary = "Créer un client")
    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO dto) {
        return clientService.createClient(dto);
    }

    @Operation(summary = "Modifier un client")
    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id,
                                  @RequestBody ClientDTO dto) {
        return clientService.updateClient(id, dto);
    }

    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
