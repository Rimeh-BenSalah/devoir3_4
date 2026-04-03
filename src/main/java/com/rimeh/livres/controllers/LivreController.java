package com.rimeh.livres.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.rimeh.livres.LivreDTO;
import com.rimeh.livres.entities.Theme;
import com.rimeh.livres.service.LivreService;

import jakarta.validation.Valid;

@Controller
public class LivreController {

    @Autowired
    LivreService livreService;

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @ModelAttribute("themes")
    public List<Theme> populateThemes() {
        return livreService.getAllThemes();
    }

    // ================= LIST =================
    @RequestMapping("/ListeLivres")
    public String listeLivres(ModelMap modelMap,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<com.rimeh.livres.entities.Livre> livs = livreService.getAllLivresParPage(page, size);

        modelMap.addAttribute("livres", livs);
        modelMap.addAttribute("pages", new int[livs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "listeLivres";
    }

    // ================= CREATE =================
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("livre", new LivreDTO());
        modelMap.addAttribute("mode", "new");
        return "formLivre";
    }

    @RequestMapping("/saveLivre")
    public String saveLivre(
            @Valid @ModelAttribute("livre") LivreDTO livre,
            BindingResult bindingResult,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("mode", "new");
            return "formLivre";
        }

        boolean isNew = (livre.getIdLivre() == null);

        livreService.saveLivre(livre);

        int currentPage = page;
        if (isNew) {
            Page<com.rimeh.livres.entities.Livre> livres = livreService.getAllLivresParPage(page, size);
            currentPage = livres.getTotalPages() - 1;
        }

        return "redirect:/ListeLivres?page=" + currentPage + "&size=" + size;
    }

    // ================= DELETE =================
    @RequestMapping("/supprimerLivre")
    public String supprimerLivre(@RequestParam("id") Long id,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "2") int size) {

        livreService.deleteLivreById(id);

        return "redirect:/ListeLivres?page=" + page + "&size=" + size;
    }

    // ================= EDIT =================
    @RequestMapping("/modifierLivre")
    public String editerLivre(@RequestParam("id") Long id,
                              ModelMap modelMap,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "2") int size) {

        LivreDTO l = livreService.getLivre(id);

        modelMap.addAttribute("livre", l);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);

        return "formLivre";
    }

    // ================= UPDATE =================
    @RequestMapping("/updateLivre")
    public String updateLivre(
            @Valid @ModelAttribute("livre") LivreDTO livre,
            BindingResult bindingResult,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("mode", "edit");
            modelMap.addAttribute("page", page);
            modelMap.addAttribute("size", size);
            return "formLivre";
        }

        livreService.updateLivre(livre);

        return "redirect:/ListeLivres?page=" + page + "&size=" + size;
    }
}