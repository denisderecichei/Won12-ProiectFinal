package org.fasttrack.ui;

import org.fasttrack.model.City;
import org.fasttrack.model.Country;
import org.fasttrack.service.CityService;
import org.fasttrack.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CountryUIController {
    private CountryService service;
    private CityService cityService;

    @Autowired
    public CountryUIController(CountryService service, CityService cityService) {
        this.service = service;
        this.cityService = cityService;
    }

    //metoda care intercepteaza countries-app, adauga datele si apoi ne trimite spre pagina de html aferenta
    @GetMapping("countries-app")
    String getCountries(Model model) {
        List<Country> countriesFromDB = service.getAllCountries();
        model.addAttribute("countries", countriesFromDB);
        return "countries-app"; // + .html
    }

    //metoda care intercepteaza cities-app pt o tara specifica, adauga datele si apoi ne trimite spre pagina de html aferenta
    @GetMapping("cities-app/{countryId}")
    String getCitiesForCountry(Model model, @PathVariable(required = false) Integer countryId) {

        List<City> allCities = cityService.findAllCitiesByCountryId(countryId);

        model.addAttribute("cities", allCities);
        model.addAttribute("countryName", allCities.get(0).getCountry().getName());
        //all cities by country
        return "cities-app";
    }

    //metoda care intercepteaza cities-app pt toate tarile, adauga datele si apoi ne trimite spre pagina de html aferenta
    @GetMapping("cities-app")
    String getAllCities(Model model) {
        List<City> allCities = cityService.getAllCities();
        model.addAttribute("cities", allCities);
        return "cities-app";
    }
}
