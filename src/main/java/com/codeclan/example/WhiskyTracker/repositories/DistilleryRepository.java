package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

public interface DistilleryRepository extends JpaRepository<Distillery, Long> {
    List<Distillery> findByRegion(String region);
}
