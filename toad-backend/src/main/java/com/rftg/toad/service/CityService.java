package com.rftg.toad.service;

import com.rftg.toad.model.City;
import com.rftg.toad.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Integer id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public void deleteCity(Integer id) {
        cityRepository.deleteById(id);
    }
}
