package com.grham.myproducts.controller;

import com.grham.myproducts.dto.LocationDTO;
import com.grham.myproducts.dto.LocationRequestDTO;
import com.grham.myproducts.exception.ResourceNotFoundException;
import com.grham.myproducts.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationRequestDTO locationRequestDTO) {
        LocationDTO locationDTO = locationService.addLocation(locationRequestDTO);
        return ResponseEntity.ok(locationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable String id) {
        Optional<LocationDTO> locationDTO = locationService.getLocationById(id);
        return locationDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
}
