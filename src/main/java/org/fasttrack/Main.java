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
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class Main {
    //valoarea se citeste din application.properties
    @Value("${file.location}")
    private String fileLocation;
    private final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner atStartup(CountryRepository repository, CityRepository cityRepo) {
        return args -> {
            List<Country> countriesFromFile = CountryReader.getAllCountries(fileLocation);
            repository.saveAll(countriesFromFile);

            List<Country> allCountriesFromDb = repository.findAll();
            //adaugam pt fiecare tara din DB cate 3 orase cu valori random
            for (Country currentCountry : allCountriesFromDb) {
                City c1 = new City(getRandomString(), random.nextInt(1000000), currentCountry);
                City c2 = new City(getRandomString(), random.nextInt(1000000), currentCountry);
                City c3 = new City(getRandomString(), random.nextInt(1000000), currentCountry);
                cityRepo.save(c1);
                cityRepo.save(c2);
                cityRepo.save(c3);
            }
        };
    }

    //metoda pt a genera stringuri random, nu e nevoie pt proiectul final
    // puteti sa faceti doar cateva obiecte de mana
    private String getRandomString() {
        UUID randomString = UUID.randomUUID();
        return randomString.toString().substring(0, 6);
    }
}