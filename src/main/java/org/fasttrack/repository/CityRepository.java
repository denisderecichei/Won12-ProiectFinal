package org.fasttrack.repository;

import org.fasttrack.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    //metoda pt a filtra orasele dupa tara in baza de date - mai eficient
    @Query("SELECT c FROM City c WHERE c.country.id = :countryId")
    List<City> findAllCitiesBelongingToCountry(@Param("countryId") Integer countryId);
}
