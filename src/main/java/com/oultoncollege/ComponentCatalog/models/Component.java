package com.oultoncollege.ComponentCatalog.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class Component implements Serializable {

    @Id
    @Column(name = "component_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;

    private String description;

    private String html;

    private String css;

    private String js;

    private String code;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany
    private List<Customization> customizations;

    public Component() {}

    public Component(Integer id, String name, String description, String html, String css, String js, String code, Language language) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.html = html;
        this.css = css;
        this.js = js;
        this.code = code;
        this.language = language;
    }

    // GETTERS AND SETTERS
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
