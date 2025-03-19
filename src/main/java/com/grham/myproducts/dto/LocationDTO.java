package com.grham.myproducts.dto;

import lombok.*;
import com.grham.myproducts.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private String id;
    private String name;
    private String room;
    private List<ProductDTO> products;

    // Constructor to map from Location entity
    public LocationDTO(String id, String name, String room, List<Product> products, String toAvoidErasure) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.products = products.stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getLocation().getId(), product.getLocation().getName(), product.getLocation().getRoom()))
                .collect(Collectors.toList());
    }
}
