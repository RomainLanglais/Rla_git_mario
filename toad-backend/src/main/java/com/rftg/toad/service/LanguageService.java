package com.rftg.toad.service;

import com.rftg.toad.model.Language;
import com.rftg.toad.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguageById(Integer id) {
        return languageRepository.findById(id).orElse(null);
    }

    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLanguage(Integer id) {
        languageRepository.deleteById(id);
    }
}
