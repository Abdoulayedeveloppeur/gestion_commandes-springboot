package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.entity.StatusCommande;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDto {

    private Long id;

    private LocalDateTime dateCommande;

    private StatusCommande status;

    private Long clientId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

}