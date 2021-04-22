package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.NurseEntries;
import com.example.models.PharmacyEntries;
import com.example.repositorys.PharmacyEntriesRepository;
import com.example.service.HospitalService;

import javassist.NotFoundException;

public class PharmacyEntriesController {
	
	@Autowired
	HospitalService hs;
	
	@Autowired
	PharmacyEntriesRepository per;

	@GetMapping
	public String findPEntries(@RequestParam(name = "id") PharmacyEntries pharmacyEntries, Model model)
	{
		List<PharmacyEntries> pe = null;
		try {
			pe = hs.findAllPharmacyEntries();
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		model.addAttribute("id", pe);
		return "id";
		
	}
}
