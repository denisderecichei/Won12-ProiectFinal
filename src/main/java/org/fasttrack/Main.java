package org.fasttrack;

import org.fasttrack.model.City;
import org.fasttrack.model.Country;
import org.fasttrack.repository.CityRepository;
import org.fasttrack.repository.CountryReader;
import org.fasttrack.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    @Value("${file.location}")
    private String fileLocation;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner atStartup(CountryRepository repository, CityRepository cityRepository) {
        return args -> {
            List<Country> countriesFromFile = CountryReader.getAllCountries(fileLocation);
            repository.saveAll(countriesFromFile);
            Country firstCountry = repository.findById(1).get();
            Country secondCountry = repository.findById(2).get();
            Country thirdCountry = repository.findById(3).get();
            City c1 = new City("Oradea", 200000, firstCountry);
            City c2 = new City("Cluj", 350000, firstCountry);
            cityRepository.save(c1);
            cityRepository.save(c2);
            City c3 = new City("C3", 30, secondCountry);
            City c4 = new City("C4", 300, secondCountry);
            City c5 = new City("C5", 10000000, thirdCountry);
            cityRepository.save(c3);
            cityRepository.save(c4);
            cityRepository.save(c5);
        };
    }
}