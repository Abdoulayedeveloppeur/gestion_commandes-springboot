package com.example.gestioncommandes.service.impl;

import com.example.gestioncommandes.dto.ProduitDTO;
import com.example.gestioncommandes.entity.Produit;
import com.example.gestioncommandes.repository.ProduitRepository;
import com.example.gestioncommandes.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public ProduitDTO creer(ProduitDTO dto) {
        Produit produit = new Produit(dto.getNom(), dto.getPrix(), dto.getStock());
        produit = produitRepository.save(produit);
        return new ProduitDTO(produit.getId(), produit.getNom(), produit.getPrix(), produit.getStock());
    }

    @Override
    public ProduitDTO modifier(Long id, ProduitDTO dto) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        produit.setNom(dto.getNom());
        produit.setPrix(dto.getPrix());
        produit.setStock(dto.getStock());
        produit = produitRepository.save(produit);
        return new ProduitDTO(produit.getId(), produit.getNom(), produit.getPrix(), produit.getStock());
    }

    @Override
    public void supprimer(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public ProduitDTO rechercher(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        return new ProduitDTO(produit.getId(), produit.getNom(), produit.getPrix(), produit.getStock());
    }

    @Override
    public List<ProduitDTO> liste() {
        return produitRepository.findAll()
                .stream()
                .map(p -> new ProduitDTO(p.getId(), p.getNom(), p.getPrix(), p.getStock()))
                .collect(Collectors.toList());
    }
}
