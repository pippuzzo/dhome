package org.openstreetart.controller;


import org.openstreetart.model.Artist;
import org.openstreetart.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.openstreetart.repository.ArtistRepository;

import java.util.List;

@RestController
@RequestMapping("api/artist")
public class ArtistController {

    private final ArtistRepository artistRepository;
    private final ArtistService artistService;

    public ArtistController(ArtistRepository artistRepository, ArtistService artistService) {
        this.artistRepository = artistRepository;
        this.artistService = artistService;
    }

    @GetMapping("")
    public List<Artist> findAll(){
        return artistRepository.findAll();
    }

    @GetMapping("/{id}")
    public Artist findById(@PathVariable Long id){
        return artistRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista non trovato!"));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Artist artist){
        if(artistRepository.existsById(artist.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista da creare già presente!");
        }
        artistRepository.save(artist);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@RequestBody Artist artist, @PathVariable Long id){
        if(!artistRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista da aggiornare non trovato!");
        }
        artistRepository.save(artist);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        if(!artistRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista da eliminare non trovato!");
        }
        artistRepository.deleteById(id);
    }

    @GetMapping("/country/{idCountry}")
    public List<Artist> findByCountryId(@PathVariable Long idCountry){
        return artistService.getArtistsByCountryId(idCountry).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Lista artisti non trovata per l'ID Country: "+idCountry));
    }



}
