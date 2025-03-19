package com.grham.myproducts.service;

import com.grham.myproducts.dto.*;
import com.grham.myproducts.exception.ResourceNotFoundException;
import com.grham.myproducts.model.Product;
import com.grham.myproducts.repository.ProductRepository;
import com.grham.myproducts.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    public ProductService(ProductRepository productRepository, LocationRepository locationRepository) {
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
    }

    public ProductDTO addProduct(ProductRequestDTO productRequestDTO) {
        var location = locationRepository.findById(productRequestDTO.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));

        var product = new Product();
        product.setName(productRequestDTO.getName());
        product.setLocation(location);

        var savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct.getId(), savedProduct.getName(), location.getId(), location.getName(), location.getRoom());
    }

    public Optional<ProductDTO> getProductById(String id) {
        return productRepository.findById(id).map(product ->
                new ProductDTO(product.getId(), product.getName(), product.getLocation().getId(), product.getLocation().getName(), product.getLocation().getRoom()));
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getLocation().getId(),
                        product.getLocation().getName(),
                        product.getLocation().getRoom()
                ))
                .collect(Collectors.toList());
    }
}
