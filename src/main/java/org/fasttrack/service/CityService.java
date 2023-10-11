package org.fasttrack.service;

import org.fasttrack.model.City;
import org.fasttrack.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllCitiesByCountryId(Integer countryId) {
        //metoda mai bruteforce, de a lua toate tarile din DB si a le filtra ulterior
//        return cityRepository.findAll().stream()
//                .filter(c -> c.getCountry().getCountryId() == countryId)
//                .collect(Collectors.toList());
        return cityRepository.findAllCitiesBelongingToCountry(countryId);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
