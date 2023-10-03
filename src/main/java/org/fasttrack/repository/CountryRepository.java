package org.fasttrack.repository;

import org.fasttrack.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAllByContinent(String continent);

    List<Country> findAllByContinentAndCapital(String continent, String capital);
}
