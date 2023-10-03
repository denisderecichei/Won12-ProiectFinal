package org.fasttrack;

import org.fasttrack.model.Country;
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
    CommandLineRunner atStartup(CountryRepository repository) {
        return args -> {
            List<Country> countriesFromFile = CountryReader.getAllCountries(fileLocation);
            repository.saveAll(countriesFromFile);
        };
    }
}