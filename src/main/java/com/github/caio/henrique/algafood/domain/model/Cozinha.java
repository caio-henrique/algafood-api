package com.github.caio.henrique.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

//    @NotBlank
//    @JsonProperty("titulo")
    @Column(nullable = false)
    private String nome;

//    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    @ToString.Exclude
    private List<Restaurante> restaurantes = new ArrayList<>();
}
