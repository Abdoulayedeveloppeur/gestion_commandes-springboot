package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.service;

import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto.ProduitDTO;

import java.util.List;

public interface ProduitService {
    List<ProduitDTO> getAllProduits();
    ProduitDTO getProduitById(Long id);
    ProduitDTO createProduit(ProduitDTO dto);
    ProduitDTO updateProduit(Long id, ProduitDTO dto);
    void deleteProduit(Long id);
}
