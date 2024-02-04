package org.openstreetart.service;

import org.openstreetart.model.Artist;
import org.openstreetart.repository.ArtistPagingAndSortingRepository;
import org.openstreetart.repository.ArtistRepository;
import org.openstreetart.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepo;
    private final ArtistPagingAndSortingRepository artistPagingRepo;
    private final CountryRepository countryRepo;

    public ArtistService(ArtistRepository artistRepo, ArtistPagingAndSortingRepository artistPagingRepo, CountryRepository countryRepo) {
        this.artistRepo = artistRepo;
        this.artistPagingRepo = artistPagingRepo;
        this.countryRepo = countryRepo;
    }

    public Optional<List<Artist>> getArtistsByCountryId(Long countryId){
        if(!countryRepo.existsById(countryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID Country inesistente!");
        }
        return artistRepo.findByCountryId(countryId);
    }

    public Page<Artist> getArtistPaged(String name, Pageable pageable){
        return artistPagingRepo.findAllByName(name, pageable);
    }
}
