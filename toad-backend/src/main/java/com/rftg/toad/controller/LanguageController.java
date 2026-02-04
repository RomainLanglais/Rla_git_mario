package com.rftg.toad.controller;

import com.rftg.toad.model.Language;
import com.rftg.toad.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public Language getLanguageById(@PathVariable Integer id) {
        return languageService.getLanguageById(id);
    }

    @PostMapping
    public Language createLanguage(@RequestBody Language language) {
        return languageService.saveLanguage(language);
    }

    @PutMapping("/{id}")
    public Language updateLanguage(@PathVariable Integer id, @RequestBody Language language) {
        language.setLanguageId(id);
        return languageService.saveLanguage(language);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Integer id) {
        languageService.deleteLanguage(id);
    }
}
