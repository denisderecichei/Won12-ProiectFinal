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
//    private final RestTemplate template = new RestTemplate();

    @Autowired
    public CountryUIController(CountryService service, CityService cityService) {
        this.service = service;
        this.cityService = cityService;
    }

    @GetMapping("countries-app")
    String getCountries(Model model) {
//        template.exchange("localhost:8080/countries", HttpMethod.GET, )
        List<Country> countriesFromDB = service.getAllCountries();
//        model.addAttribute("name", name);
        model.addAttribute("countries", countriesFromDB);
        return "countries-app"; // + .html
    }

    @GetMapping("cities/{countryId}")
    String getCitiesForCountry(Model model, @PathVariable Integer countryId) {
        Country country = service.getCountryById(countryId);
        if (country != null) {
            model.addAttribute("countryName", country.getName());
            model.addAttribute("countryId", country.getCountryId());
            List<City> citiesForCountry = cityService.getAllCitiesByCountry(countryId);
            model.addAttribute("cities", citiesForCountry);
            return "cities";
        } else {
            return "error";
        }
    }
}
