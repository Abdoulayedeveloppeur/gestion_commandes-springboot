package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.ProduitDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.ProduitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final Map<Long, ProduitDTO> produits = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public List<ProduitDTO> getAllProduits() {
        return new ArrayList<>(produits.values());
    }

    @Override
    public ProduitDTO getProduitById(Long id) {
        ProduitDTO produit = produits.get(id);
        if (produit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable");
        }
        return produit;
    }

    @Override
    public ProduitDTO createProduit(ProduitDTO dto) {
        ProduitDTO produit = new ProduitDTO();
        produit.setId(sequence.getAndIncrement());
        produit.setNom(dto.getNom());
        produit.setPrix(dto.getPrix());
        produit.setStock(dto.getStock());
        produits.put(produit.getId(), produit);
        return produit;
    }

    @Override
    public ProduitDTO updateProduit(Long id, ProduitDTO dto) {
        ProduitDTO existing = produits.get(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable");
        }
        existing.setNom(dto.getNom());
        existing.setPrix(dto.getPrix());
        existing.setStock(dto.getStock());
        return existing;
    }

    @Override
    public void deleteProduit(Long id) {
        if (!produits.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable");
        }
        produits.remove(id);
    }
}
