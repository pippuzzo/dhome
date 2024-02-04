package org.openstreetart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.openstreetart.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArtistRepository extends CrudRepository<Artist,Long> {


    public List<Artist> findAll();

    public Optional<Artist> findById(Long id);

    public Optional<List<Artist>> findByCountryId(Long id);

    public boolean existsById(Long id);

    public void deleteById(Long id);
}

