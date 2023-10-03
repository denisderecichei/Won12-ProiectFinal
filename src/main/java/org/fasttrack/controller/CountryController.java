package org.fasttrack.controller;

import org.fasttrack.model.Country;
import org.fasttrack.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {
    private CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return service.getAllCountries();
    }

    @GetMapping("{countryId}")
    public Country getCountryById(@PathVariable int countryId) {
        return service.getCountryById(countryId);
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return service.addCountry(country);
    }

    @PutMapping("{countryId}")
    public Country putCountry(@PathVariable int countryId, @RequestBody Country country) {
        return service.replaceCountry(countryId, country);
    }


    @DeleteMapping("{countryId}")
    public String removeCountry(@PathVariable int countryId) {
        return service.removeCountry(countryId);
    }

}