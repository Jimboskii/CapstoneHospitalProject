package com.example.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.models.Patient;


@Repository
@CrossOrigin
public interface PatientRepository extends JpaRepository<Patient, Long> {

	
	public Optional<Patient> findByPatientId(Long id);
	
	public List<Patient> findByAdmitted(String admit);
	
}
