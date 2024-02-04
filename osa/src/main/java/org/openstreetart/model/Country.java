package org.openstreetart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name="country")
public @Data class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;
    private @Getter @Setter String name;
    private @Getter @Setter String alpha_2;
    private @Getter @Setter String alpha_3;


    public Country() {}

    public Country(Long id) {
        this.setId(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Country))
            return false;
        Country country = (Country) o;
        return Objects.equals(this.getId(), country.getId()) && Objects.equals(this.name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), this.name);
    }

    @Override
    public String toString() {
        return "Artista: {id=" + getId() + ", nome='" + this.name + "'}";
    }

}
