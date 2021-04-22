package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.Drug;
import com.example.models.DrugTypes;
import com.example.repositorys.DrugsRepository;
import com.example.service.HospitalService;

import javassist.NotFoundException;

public class DrugController {
	
	@Autowired
	DrugsRepository drugsRepository;
	
	
	@Autowired
	HospitalService hs;
	
	
	@GetMapping
	public String findDrugs(@RequestParam(name = "drugName") DrugTypes drugName, Model model)
	{
		List<Drug> d = null;
		try {
			d = hs.findDrug(drugName);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("name", d);
		return "name";

		
	}

}
