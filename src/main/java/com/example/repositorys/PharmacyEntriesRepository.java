package com.example.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.models.PharmacyEntries;

@Repository
@CrossOrigin
public interface PharmacyEntriesRepository extends JpaRepository<PharmacyEntries, Long> {

}
