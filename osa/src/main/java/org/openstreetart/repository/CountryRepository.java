package org.openstreetart.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.openstreetart.model.Country;

import java.util.List;


@Repository
public interface CountryRepository extends ListCrudRepository<Country,Long>  {

  List<Country> findAll();
}

