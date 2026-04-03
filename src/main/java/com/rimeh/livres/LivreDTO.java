package com.rimeh.livres;


import java.util.Date;

import com.rimeh.livres.entities.Theme;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivreDTO {
	private Long idLivre;
	private String nomLivre;
	private String auteur;
	private Double prixLivre;
	private Date datedepublication;
	private String email;
	//private Theme theme;
	private String nomthe;
}
