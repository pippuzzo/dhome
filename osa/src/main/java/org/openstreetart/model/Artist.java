package org.openstreetart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name="artist")
public @Data class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;
    private @Getter @Setter String name;
    private @Getter @Setter String biografia;
    private @Getter @Setter String biography;
    private @Getter @Setter String info;
    private @Getter @Setter String information;

    // mono-directional many-to-one association to Country
    @ManyToOne
    @JoinColumn(name = "country")
    @Getter @Setter
    private Country country;

    // mono-directional many-to-one association to Contact
    @ManyToOne
    @JoinColumn(name = "contact")
    @Getter @Setter
    private Contact contact;
    /*
    // bi-directional many-to-many association to Opera
    @ManyToMany(mappedBy = "artisti", fetch = FetchType.LAZY)
    private List<Opera> opere;
*/


    public Artist() {}

    public Artist(Long id) {
        this.setId(id);
    }

    public Artist(String name, String info, String information, String biografia, String biography, Contact contact, Country country) {
        this.name = name;
        this.info = info;
        this.information = information;
        this.biografia = biografia;
        this.biography = biography;
        this.contact = contact;
        this.country = country;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Artist))
            return false;
        Artist artista = (Artist) o;
        return Objects.equals(this.getId(), artista.getId()) && Objects.equals(this.name, artista.name);
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
