package com.oultoncollege.ComponentCatalog.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Customization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customization_id")
    private Integer id;

    private String name;

    private String html;

    private String css;

    private String js;

    @NotNull
    @ManyToMany(mappedBy = "customizations")
    private List<Component> components;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
