package com.rftg.toad.service;

import com.rftg.toad.model.Status;
import com.rftg.toad.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status getStatusById(Integer id) {
        return statusRepository.findById(id).orElse(null);
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatus(Integer id) {
        statusRepository.deleteById(id);
    }
}
