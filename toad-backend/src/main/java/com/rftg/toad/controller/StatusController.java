package com.rftg.toad.controller;

import com.rftg.toad.model.Status;
import com.rftg.toad.service.StatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @GetMapping("/{id}")
    public Status getStatusById(@PathVariable Integer id) {
        return statusService.getStatusById(id);
    }

    @PostMapping
    public Status createStatus(@RequestBody Status status) {
        return statusService.saveStatus(status);
    }

    @PutMapping("/{id}")
    public Status updateStatus(@PathVariable Integer id, @RequestBody Status status) {
        status.setStatusId(id);
        return statusService.saveStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable Integer id) {
        statusService.deleteStatus(id);
    }
}
