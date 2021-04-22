package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Patient;
//import com.example.repositorys.DrugsRepository;
import com.example.repositorys.PatientRepository;
import com.example.service.HospitalService;

import javassist.NotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PatientController {
	
	@Autowired
	PatientRepository patientRepository;
	
//	@Autowired
//	DrugsRepository drugsRepository;
	
	@Autowired
	HospitalService hs;
	
	
	@GetMapping
	public String findPatients(@RequestParam(name = "patientId") Long patientId, Model model)
	{
		Patient p = null;
		try {
			p = hs.findPatient(patientId);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("id", p);
		return "id";

		
	}
	

}
