package com.flagship.repository;

import com.flagship.model.db.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
  Optional<Country> findByCountryId(String countryId);
}
