package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.controller;

import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.ProduitDTO;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
@Tag(name = "Produits", description = "Gestion des produits")
public class ProduitController {

    private final ProduitService produitService;

    @Operation(summary = "Lister tous les produits")
    @GetMapping
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }

    @Operation(summary = "Rechercher un produit par son ID")
    @GetMapping("/{id}")
    public ProduitDTO getProduit(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    @Operation(summary = "Créer un produit")
    @PostMapping
    public ProduitDTO createProduit(@RequestBody ProduitDTO dto) {
        return produitService.createProduit(dto);
    }

    @Operation(summary = "Modifier un produit")
    @PutMapping("/{id}")
    public ProduitDTO updateProduit(@PathVariable Long id,
                                    @RequestBody ProduitDTO dto) {
        return produitService.updateProduit(id, dto);
    }

    @Operation(summary = "Supprimer un produit")
    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }
}