package com.example.gestioncommandes.service.impl;

import com.example.gestioncommandes.dto.CommandeDTO;
import com.example.gestioncommandes.dto.LigneCommandeDTO;
import com.example.gestioncommandes.entity.Commande;
import com.example.gestioncommandes.entity.LigneCommande;
import com.example.gestioncommandes.entity.Produit;
import com.example.gestioncommandes.repository.CommandeRepository;
import com.example.gestioncommandes.repository.LigneCommandeRepository;
import com.example.gestioncommandes.repository.ProduitRepository;
import com.example.gestioncommandes.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public CommandeDTO creer(CommandeDTO dto) {
        Commande commande = new Commande(dto.getDateCommande(), dto.getStatus());
        commande = commandeRepository.save(commande);
        return new CommandeDTO(commande.getId(), commande.getDateCommande(), commande.getStatus(), commande.getTotal());
    }

    @Override
    public CommandeDTO ajouterLigne(Long commandeId, LigneCommandeDTO ligneDTO) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if ("VALIDATED".equals(commande.getStatus())) {
            throw new RuntimeException("Impossible de modifier une commande validée");
        }

        Produit produit = produitRepository.findById(ligneDTO.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        if (produit.getStock() < ligneDTO.getQuantite()) {
            throw new RuntimeException("Stock insuffisant pour " + produit.getNom());
        }

        produit.setStock(produit.getStock() - ligneDTO.getQuantite());
        produitRepository.save(produit);

        LigneCommande ligne = new LigneCommande(produit, ligneDTO.getQuantite(), ligneDTO.getPrixUnitaire(), commande);
        ligneCommandeRepository.save(ligne);

        commande.getLignes().add(ligne);
        commande.setTotal(
            commande.getLignes().stream()
                .mapToDouble(l -> l.getPrixUnitaire() * l.getQuantite())
                .sum()
        );
        commandeRepository.save(commande);

        return new CommandeDTO(commande.getId(), commande.getDateCommande(), commande.getStatus(), commande.getTotal());
    }

    @Override
    public CommandeDTO supprimerLigne(Long commandeId, Long ligneId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if ("VALIDATED".equals(commande.getStatus())) {
            throw new RuntimeException("Impossible de modifier une commande validée");
        }

        LigneCommande ligne = ligneCommandeRepository.findById(ligneId)
                .orElseThrow(() -> new RuntimeException("Ligne introuvable"));

        commande.getLignes().remove(ligne);
        ligneCommandeRepository.delete(ligne);

        commande.setTotal(
            commande.getLignes().stream()
                .mapToDouble(l -> l.getPrixUnitaire() * l.getQuantite())
                .sum()
        );
        commandeRepository.save(commande);

        return new CommandeDTO(commande.getId(), commande.getDateCommande(), commande.getStatus(), commande.getTotal());
    }

    @Override
    public CommandeDTO modifier(Long id, CommandeDTO dto) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if ("VALIDATED".equals(commande.getStatus())) {
            throw new RuntimeException("Impossible de modifier une commande validée");
        }

        commande.setDateCommande(dto.getDateCommande());
        commande.setStatus(dto.getStatus());
        commandeRepository.save(commande);

        return new CommandeDTO(commande.getId(), commande.getDateCommande(), commande.getStatus(), commande.getTotal());
    }

    @Override
    public void supprimer(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if ("VALIDATED".equals(commande.getStatus())) {
            throw new RuntimeException("Impossible de supprimer une commande validée");
        }

        commandeRepository.delete(commande);
    }

    @Override
    public CommandeDTO consulter(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        return new CommandeDTO(commande.getId(), commande.getDateCommande(), commande.getStatus(), commande.getTotal());
    }
}
