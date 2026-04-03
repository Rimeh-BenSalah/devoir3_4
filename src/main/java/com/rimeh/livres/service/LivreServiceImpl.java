package com.rimeh.livres.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rimeh.livres.LivreDTO;
import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Theme;
import com.rimeh.livres.repos.LivreRepository;
import com.rimeh.livres.repos.ThemeRepository;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired 
    private LivreRepository livreRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ModelMapper modelMapper;

    // ================= CRUD DTO =================

    @Override
    public LivreDTO saveLivre(LivreDTO l) {
        Livre livre = convertDtoToEntity(l);
        Livre savedLivre = livreRepository.save(livre);
        return convertEntityToDto(savedLivre);
    }

    @Override
    public LivreDTO updateLivre(LivreDTO l) {
        Livre livre = convertDtoToEntity(l);
        Livre updatedLivre = livreRepository.save(livre);
        return convertEntityToDto(updatedLivre);
    }

    @Override
    public LivreDTO getLivre(Long id) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre not found"));
        return convertEntityToDto(livre);
    }

    @Override
    public List<LivreDTO> getAllLivres() {
        return livreRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // ================= Pagination =================

    @Override
    public Page<Livre> getAllLivresParPage(int page, int size) {
        return livreRepository.findAll(PageRequest.of(page, size));
    }

    // ================= Recherches =================

    @Override
    public List<Livre> findByNomLivre(String nom) {
        return livreRepository.findByNomLivre(nom);
    }

    @Override
    public List<Livre> findByNomLivreContains(String nom) {
        return livreRepository.findByNomLivreContains(nom);
    }

    @Override
    public List<Livre> findByNomPrix(String nom, Double prix) {
        return livreRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Livre> findByTheme(Theme theme) {
        return livreRepository.findByTheme(theme);
    }

    @Override
    public List<Livre> findByThemeIdThe(Long id) {
        return livreRepository.findByThemeIdThe(id);
    }

    @Override
    public List<Livre> findByOrderByNomLivreAsc() {
        return livreRepository.findByOrderByNomLivreAsc();
    }

    @Override
    public List<Livre> trierLivresNomsPrix() {
        return livreRepository.trierLivresNomsPrix();
    }

    // ================= Themes =================

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    // ================= Delete =================

    @Override
    public void deleteLivre(Livre l) {
        livreRepository.delete(l);
    }

    @Override
    public void deleteLivreById(Long id) {
        livreRepository.deleteById(id);
    }

    // ================= Mapping =================

    @Override
    public LivreDTO convertEntityToDto(Livre l) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper.map(l, LivreDTO.class);
    }

    @Override
    public Livre convertDtoToEntity(LivreDTO livreDto) {
        return modelMapper.map(livreDto, Livre.class);
    }
}