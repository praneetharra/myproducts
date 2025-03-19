package com.grham.myproducts.service;

import com.grham.myproducts.dto.*;
import com.grham.myproducts.model.Location;
import com.grham.myproducts.repository.LocationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationDTO addLocation(LocationRequestDTO locationRequestDTO) {
        // Step 1: Create a new Location entity from the request DTO
        var location = new Location();
        location.setName(locationRequestDTO.getName());
        location.setRoom(locationRequestDTO.getRoom());

        // Step 2: Save the Location entity to the database
        locationRepository.save(location);

        // Step 3: Return a LocationDTO with data from the saved Location entity
        return new LocationDTO(location.getId(), location.getName(), location.getRoom(), location.getProducts(), "toAvoidErasure");
    }

    public Optional<LocationDTO> getLocationById(String id) {
        return locationRepository.findById(id).map(location ->
                new LocationDTO(location.getId(), location.getName(), location.getRoom(), location.getProducts().stream()
                        .map(product -> new ProductDTO(product.getId(), product.getName(), location.getId(), location.getName(), location.getRoom()))
                        .collect(Collectors.toList())));
    }

    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(location -> new LocationDTO(location.getId(), location.getName(), location.getRoom(), location.getProducts(), "toAvoidErasure"))
                .collect(Collectors.toList());
    }
}
