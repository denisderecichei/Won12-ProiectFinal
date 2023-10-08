package org.fasttrack.service;

import org.fasttrack.model.City;
import org.fasttrack.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository repository;

    @Autowired
    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public List<City> getAllCitiesByCountry(Integer countryId) {
        return repository.findByCountryId(countryId);
    }
}
