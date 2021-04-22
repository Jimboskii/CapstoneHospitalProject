package com.example.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.models.Drug;
import com.example.models.DrugTypes;

@Repository
//@CrossOrigin(methods = RequestMethod.POST, allowCredentials = "false")
public interface DrugsRepository extends JpaRepository<Drug, Long> {
	List<Drug> findByDrugType(DrugTypes DT);
	
	Optional<Drug> findByDrugName(String DN);

}
