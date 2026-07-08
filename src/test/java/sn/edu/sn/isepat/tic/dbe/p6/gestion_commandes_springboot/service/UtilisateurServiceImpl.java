package com.example.gestioncommandes.service.impl;

import com.example.gestioncommandes.dto.UtilisateurDTO;
import com.example.gestioncommandes.entity.Utilisateur;
import com.example.gestioncommandes.repository.UtilisateurRepository;
import com.example.gestioncommandes.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UtilisateurDTO creer(UtilisateurDTO dto) {
        Utilisateur utilisateur = new Utilisateur(dto.getNom(), dto.getEmail());
        utilisateur = utilisateurRepository.save(utilisateur);
        return new UtilisateurDTO(utilisateur.getId(), utilisateur.getNom(), utilisateur.getEmail());
    }

    @Override
    public UtilisateurDTO modifier(Long id, UtilisateurDTO dto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur = utilisateurRepository.save(utilisateur);
        return new UtilisateurDTO(utilisateur.getId(), utilisateur.getNom(), utilisateur.getEmail());
    }

    @Override
    public void supprimer(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDTO rechercher(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return new UtilisateurDTO(utilisateur.getId(), utilisateur.getNom(), utilisateur.getEmail());
    }

    @Override
    public List<UtilisateurDTO> liste() {
        return utilisateurRepository.findAll()
                .stream()
                .map(u -> new UtilisateurDTO(u.getId(), u.getNom(), u.getEmail()))
                .collect(Collectors.toList());
    }
