package net.codejava;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String species;

    // Otros atributos y métodos
    
    // Constructor vacío (obligatorio para JPA)
    public Pet() {
    }

    // Constructor con parámetros
    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
    }

    // Getters y Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
