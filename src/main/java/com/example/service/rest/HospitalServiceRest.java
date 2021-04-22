package com.example.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Drug;
import com.example.models.DrugTypes;
import com.example.models.NurseEntries;
import com.example.models.Patient;
import com.example.models.PharmacyEntries;
import com.example.service.HospitalService;

import javassist.NotFoundException;


@CrossOrigin
@RestController
@RequestMapping(path = "/hospital")
public class HospitalServiceRest {

	@Autowired 
	HospitalService hs;
	
////////////////////////// PATIENT ///////////////////////
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@GetMapping("patient/{id}")
	// produces = { "application/json" })
	public Patient findByName(@PathVariable Long id) throws NotFoundException {
		Patient p = hs.findPatient(id);
		return p;

	}
	
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@GetMapping("patient/admit/{admit}")
	// produces = { "application/json" })
	public List<Patient> findByAdmitted(@PathVariable String admit) throws NotFoundException {
		List<Patient> list = hs.findAdmittedPatient(admit);
		return list;

	}
	
	////@PreAuthorize("hasAuthority('PHARMACIST')")
	@GetMapping("patient/list")
	// produces = { "application/json" })
	public List<Patient> findEveryone() throws NotFoundException {
		List<Patient> p = hs.findAll();
		return p;

	}
	
	////@PreAuthorize("hasAuthority('PHARMACIST')")
	@PostMapping("patient/insert")
	public String insert(@RequestBody Patient p) {
		return hs.insertPatient(p);
	}
	

////////////////////////// DRUG ///////////////////////
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@GetMapping("drug/{type}")
	// produces = { "application/json" })
	public List<Drug> findByName(@PathVariable DrugTypes type) throws NotFoundException {
		List<Drug> d  = hs.findDrug(type);
		return d;

	}
	
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@GetMapping("drug/list")
	// produces = { "application/json" })
	public List<Drug> findAllDrugs() throws NotFoundException {
		List<Drug> d = hs.findAllDrugs();
		return d;

	}
	
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@PostMapping("drug/insert")
	public String insert(@RequestBody Drug d) {
		return hs.insertDrug(d);
	}
	
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@PostMapping("drug/update")
	public String update(@RequestBody Drug d) {
		return hs.updateDrug(d);
	}

////////////////////////// NURSE ENTRIES ///////////////////////
	//@PreAuthorize("hasAuthority('NURSE')")
	@GetMapping("nurseentry/list")
	// produces = { "application/json" })
	public List<NurseEntries> findAllNurseEntries() throws NotFoundException {
		List<NurseEntries> ne = hs.findAllNurseEntries();
		return ne;

	}
	
	//@PreAuthorize("hasAuthority('NURSE')")
	@PostMapping("nurseentry/insert")
	public String insert(@RequestBody NurseEntries ne) {
		return hs.insertNurseEntries(ne);
	}
	
	
	//@PreAuthorize("hasAuthority('NURSE')")
	@GetMapping("nurseentry/list/{id}")
	// produces = { "application/json" })
	public NurseEntries findByNurseEntryId(@PathVariable Long id) throws NotFoundException {
		NurseEntries ne = hs.findNurseEntries(id);
		return ne;

	}

////////////////////////// PHARMACY ENTRIES ///////////////////////
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@GetMapping("pharmacyentry/list")
	// produces = { "application/json" })
	public List<PharmacyEntries> findAllPharmacyEntries() throws NotFoundException {
		List<PharmacyEntries> pe = hs.findAllPharmacyEntries();
		return pe;

	}
	
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@PostMapping("pharmacyentry/insert")
	public String insert(@RequestBody PharmacyEntries pe) {
		return hs.insertPharmacyEntries(pe);
	}
	//@PreAuthorize("hasAuthority('PHARMACIST')")
	@GetMapping("pharmacyentry/list/{id}")
	// produces = { "application/json" })
	public PharmacyEntries findByPharmacyEntryId(@PathVariable Long id) throws NotFoundException {
		PharmacyEntries pe = hs.findPharmacyEntries(id);
		return pe;

	}
	
}
