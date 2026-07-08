package sn.edu.sn.isepat.tic.dbe.p6.gestion_commandes_springboot.dto;

import java.math.BigDecimal;

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
public class ProduitDto {

    private Long id;

    private String nom;

    private BigDecimal prix;

    private Integer stock;
}