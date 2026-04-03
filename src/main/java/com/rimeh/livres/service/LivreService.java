package com.rimeh.livres.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rimeh.livres.LivreDTO;
import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Theme;

public interface LivreService {

    // CRUD avec DTO
    LivreDTO saveLivre(LivreDTO l);
    LivreDTO updateLivre(LivreDTO l);
    LivreDTO getLivre(Long id);
    List<LivreDTO> getAllLivres();

    // Pagination
    Page<Livre> getAllLivresParPage(int page, int size);

    // Recherches
    List<Livre> findByNomLivre(String nom);
    List<Livre> findByNomLivreContains(String nom);
    List<Livre> findByNomPrix(String nom, Double prix);
    List<Livre> findByTheme(Theme theme);
    List<Livre> findByThemeIdThe(Long id);
    List<Livre> findByOrderByNomLivreAsc();
    List<Livre> trierLivresNomsPrix();

    // Themes
    List<Theme> getAllThemes();

    // Conversions
    LivreDTO convertEntityToDto(Livre l);
    Livre convertDtoToEntity(LivreDTO livreDto);

    // Suppression
    void deleteLivre(Livre l);
    void deleteLivreById(Long id);
}