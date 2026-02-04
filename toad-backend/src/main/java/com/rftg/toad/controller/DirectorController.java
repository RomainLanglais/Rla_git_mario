package com.rftg.toad.controller;

import com.rftg.toad.model.Director;
import com.rftg.toad.service.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public Director getDirectorById(@PathVariable Integer id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping
    public Director createDirector(@RequestBody Director director) {
        return directorService.saveDirector(director);
    }

    @PutMapping("/{id}")
    public Director updateDirector(@PathVariable Integer id, @RequestBody Director director) {
        director.setDirectorId(id);
        return directorService.saveDirector(director);
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable Integer id) {
        directorService.deleteDirector(id);
    }
}
