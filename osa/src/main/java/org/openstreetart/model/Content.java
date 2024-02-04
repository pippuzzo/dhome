package org.openstreetart.model;


import java.time.LocalDateTime;

public record Content(
       Integer id,
       String name,
       Type contentType,
       Status status,
       LocalDateTime dateCreated,
       LocalDateTime dateUpdated){}