package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.NurseEntries;
import com.example.repositorys.NurseEntriesRepository;
import com.example.service.HospitalService;

import javassist.NotFoundException;

public class NurseEntriesController {

	@Autowired
	NurseEntriesRepository ner;
	
	@Autowired
	HospitalService hs;
	
	@GetMapping
	public String findREntries(@RequestParam(name = "id") NurseEntries nurseEntries, Model model)
	{
		List<NurseEntries> ne = null;
		try {
			ne = hs.findAllNurseEntries();
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		model.addAttribute("id", ne);
		return "id";
		
	}
	
}
