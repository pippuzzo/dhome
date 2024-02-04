package org.openstreetart.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.openstreetart.model.Content;
import org.openstreetart.model.Status;
import org.openstreetart.model.Type;
import org.openstreetart.repository.ContentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("")
    public List<Content> findAll(){
        return contentRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Content> findById(@PathVariable Integer id){
        List<Content> contentList = new ArrayList<>();
        Content content = contentRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content non trovato!"));
        contentList.add(content);
        return contentList;
    }

    @PostConstruct
    private void init(){
        Content content = new Content(1,"My First Content", Type.ARTIST, Status.PROPOSE, LocalDateTime.now(), null);
        contentRepository.add(content);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content) throws Exception {
        if(contentRepository.isExist(content.id())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content da creare già presente!");
        }
        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        if(!contentRepository.isExist(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content da aggiornare non trovato!");
        }
        contentRepository.update(id, content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        if(!contentRepository.isExist(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content da eliminare non trovato!");
        }
        contentRepository.delete(id);
    }

}
