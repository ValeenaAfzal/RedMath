package com.project.first.redMath.RestTemplate;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class News {
    @Id
    private int id;
    private String category;
    private String description;
    private String name;
    private String url;
    private String language;
    private String country;
}
