package com.oultoncollege.ComponentCatalog.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Entity
public class Language implements Serializable {

    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "language")
    private List<Component> components;

    public Integer getLangId() {
        return id;
    }

    public void setLangId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getComponents() {
        components.sort(Comparator.comparing(Component::getName));
        return components;
    }
}
