package org.fasttrack.ui;

import org.fasttrack.model.Country;
import org.fasttrack.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CountryUIController {
    private CountryService service;
//    private final RestTemplate template = new RestTemplate();

    @Autowired
    public CountryUIController(CountryService service) {
        this.service = service;
    }

    @GetMapping("countries-app")
    String getCountries(Model model) {
//        template.exchange("localhost:8080/countries", HttpMethod.GET, )
        List<Country> countriesFromDB = service.getAllCountries();
//        model.addAttribute("name", name);
        model.addAttribute("countries", countriesFromDB);
        return "countries-app"; // + .html
    }
}
