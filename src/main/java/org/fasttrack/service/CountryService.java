package org.fasttrack.service;

import org.fasttrack.exception.EntityNotFoundException;
import org.fasttrack.model.Country;
import org.fasttrack.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> getAllCountries() {
        return repository.findAll();
    }

    public List<Country> getAllCountriesByContinent(String continentName) {
        return repository.findAllByContinent(continentName);
    }

    public Country addCountry(Country country) {
        return repository.save(country);
    }

    public Country getCountryById(int countryId) {
        Optional<Country> foundCountry = repository.findById(countryId);
        if (foundCountry.isPresent()) {
            return foundCountry.get();
        } else {
            throw new EntityNotFoundException("Nu a fost gasita tara cu id-ul " + countryId, countryId);
        }
    }

    public String removeCountry(int countryId) {
        repository.deleteById(countryId);
        return "Sters cu succes";
    }

    public Country replaceCountry(int countryId, Country country) {
        country.setCountryId(countryId);
        return repository.save(country);
    }
}
