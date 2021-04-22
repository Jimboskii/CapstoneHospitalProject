package com.example.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.models.NurseEntries;

@Repository
@CrossOrigin
public interface NurseEntriesRepository extends JpaRepository<NurseEntries, Long> {

}
