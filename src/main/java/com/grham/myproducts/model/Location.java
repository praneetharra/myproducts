package com.grham.myproducts.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String room;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Product> products;
}
