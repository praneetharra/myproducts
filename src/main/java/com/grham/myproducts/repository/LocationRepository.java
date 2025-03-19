package com.grham.myproducts.repository;

import com.grham.myproducts.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
