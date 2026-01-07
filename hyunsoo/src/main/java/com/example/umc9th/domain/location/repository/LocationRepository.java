package com.example.umc9th.domain.location.repository;

import com.example.umc9th.domain.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
