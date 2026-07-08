package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommandeDto {

    private Long id;

    private Integer quantite;

    private Long commandeId;

    private Long produitId;

}