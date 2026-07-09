package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.controller;

import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.CommandeDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.LigneCommandeDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.CommandeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
@Tag(name = "Commandes", description = "Gestion des commandes")
public class CommandeController {

    private final CommandeService commandeService;

    @Operation(summary = "Lister les commandes")
    @GetMapping
    public List<CommandeDTO> getAll() {
        return commandeService.getAll();
    }

    @Operation(summary = "Commande par ID")
    @GetMapping("/{id}")
    public CommandeDTO getById(@PathVariable Long id) {
        return commandeService.getById(id);
    }

    @Operation(summary = "Créer une commande")
    @PostMapping
    public CommandeDTO create(@RequestBody CommandeDTO dto) {
        return commandeService.create(dto);
    }

    @Operation(summary = "Modifier une commande")
    @PutMapping("/{id}")
    public CommandeDTO update(@PathVariable Long id,
                              @RequestBody CommandeDTO dto) {
        return commandeService.update(id, dto);
    }

    @Operation(summary = "Supprimer une commande")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commandeService.delete(id);
    }

    @Operation(summary = "Valider une commande")
    @PostMapping("/{id}/valider")
    public CommandeDTO valider(@PathVariable Long id) {
        return commandeService.valider(id);
    }

    @Operation(summary = "Ajouter une ligne de commande")
    @PostMapping("/{id}/ligne")
    public CommandeDTO ajouterLigne(@PathVariable Long id,
                                    @RequestBody LigneCommandeDTO dto) {
        return commandeService.ajouterLigne(id, dto);
    }

    @Operation(summary = "Commandes d'un client")
    @GetMapping("/client/{clientId}")
    public List<CommandeDTO> commandesClient(@PathVariable Long clientId) {
        return commandeService.commandesClient(clientId);
    }

    @Operation(summary = "Commandes entre deux dates")
    @GetMapping("/entre-dates")
    public List<CommandeDTO> entreDates(
            @RequestParam String debut,
            @RequestParam String fin) {
        return commandeService.entreDates(debut, fin);
    }

    @Operation(summary = "Chiffre d'affaires")
    @GetMapping("/statistiques/chiffre-affaires")
    public Double chiffreAffaires() {
        return commandeService.chiffreAffaires();
    }

    @Operation(summary = "Total des commandes par client")
    @GetMapping("/statistiques/client")
    public Object totalParClient() {
        return commandeService.totalParClient();
    }
}