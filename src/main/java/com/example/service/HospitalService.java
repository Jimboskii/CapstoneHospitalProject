package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.mail.demo.model.MailMessage;
import com.example.models.Drug;
import com.example.models.DrugTypes;
import com.example.models.NurseEntries;
import com.example.models.Patient;
import com.example.models.PharmacyEntries;
import com.example.repositorys.DrugsRepository;
import com.example.repositorys.NurseEntriesRepository;
import com.example.repositorys.PatientRepository;
import com.example.repositorys.PharmacyEntriesRepository;

import javassist.NotFoundException;

@Service
public class HospitalService {

	@Autowired
	PatientRepository pr;
	
	@Autowired
	DrugsRepository dr;
	
	@Autowired
	NurseEntriesRepository ner;
	
	@Autowired
	PharmacyEntriesRepository per;
	
	private JavaMailSender javaMailSender;
	
	//////////////////////// PATIENTS /////////////////////////////
	public Patient findPatient(Long id) throws NotFoundException{
		Patient p = null;
		Optional<Patient> opt = pr.findByPatientId(id);
		if (opt.isPresent()) {
			p = opt.get();
		} else {
			throw new NotFoundException(id + " doesnt exist");
		}
		return p;
		
	}
	
	
	public List<Patient> findAdmittedPatient(String admit) throws NotFoundException{
		List<Patient> list = pr.findByAdmitted(admit);
		if (list != null) {

		} else {
			throw new NotFoundException("There are no patients");
		}
		return list;
		
	}

	public List<Patient> findAll() throws NotFoundException {
//		Patient p = null;
		List<Patient> list = pr.findAll();
		if (list != null) {
//			for(int i = 0; i < list.size();  i++)
//				p = list.get(i);
		} else {
			throw new NotFoundException("There are no patients");
		}
		return list;
	}
	
	
	public String insertPatient(Patient p) {

		Optional<Patient> opt = pr.findById(p.getPatientId());

		if (!opt.isPresent()) {
			pr.save(p);
			return "success";
		}
		return p.getPatientId() + " already exist";
	}
	
	
//////////////////////// DRUGS /////////////////////////////
	
	public List<Drug> findDrug(DrugTypes d) throws NotFoundException{// throws DepartmentNotFoundException {
		//Drug drug = null;
		List<Drug> list = dr.findByDrugType(d);
		if (list != null) {
//			d = opt.get();
		} else {
			throw new NotFoundException(d + " doesnt exist");
		}
		return list;
	}
	
	public List<Drug> findAllDrugs() throws NotFoundException {
//		Patient p = null;
		List<Drug> list = dr.findAll();
		if (list != null) {
//			for(int i = 0; i < list.size();  i++)
//				p = list.get(i);
		} else {
			throw new NotFoundException("There are no drugs.");
		}
		return list;
	}
	
	public String insertDrug(Drug d) {

		Optional<Drug> opt = dr.findByDrugName(d.getDrugName());

		if (!opt.isPresent()) {
			dr.save(d);
			return d.getDrugName() + " has been added.";
		}
		return d.getDrugName() + " already exist.";
	}
	
	public String updateDrug(Drug d) {

		Optional<Drug> opt = dr.findByDrugName(d.getDrugName());

		if (opt.isPresent()) {
			dr.save(d);
			return d.getDrugName() + " has been added.";
		}
		return d.getDrugName() + " does not exist.";
	}
	

//////////////////////// NURSE ENTRIES /////////////////////////////
	public NurseEntries findNurseEntries(Long id) throws NotFoundException{
		NurseEntries ne = null;
		Optional<NurseEntries> opt = ner.findById(id);
		if (opt.isPresent()) {
			ne = opt.get();
		} else {
			throw new NotFoundException("Entry with id:"+ id + " doesnt exist");
		}
		return ne;
	}
	
	public List<NurseEntries> findAllNurseEntries() throws NotFoundException{// throws DepartmentNotFoundException {
		//Drug drug = null;
		List<NurseEntries> list = ner.findAll();
		if (list != null) {
//			
		} else {
			throw new NotFoundException("There are no Nurse Records");
		}
		return list;
	}
	
	
	public String insertNurseEntries(NurseEntries ne) {

		ner.save(ne);
		return "Description to patient with ID \"" + ne.getId() + "\" has been added.";
	}

//////////////////////// PHARMACY ENTRIES /////////////////////////////
	
	public PharmacyEntries findPharmacyEntries(Long id) throws NotFoundException{
		PharmacyEntries pe = null;
		Optional<PharmacyEntries> opt = per.findById(id);
		if (opt.isPresent()) {
			pe = opt.get();
		} else {
			throw new NotFoundException("Entry with id:"+ id + " doesnt exist");
		}
		return pe;
	}
	public List<PharmacyEntries> findAllPharmacyEntries() throws NotFoundException{// throws DepartmentNotFoundException {
		//Drug drug = null;
		List<PharmacyEntries> list = per.findAll();
		if (list != null) {
//			
		} else {
			throw new NotFoundException("There are no Pharmacy Records");
		}
		return list;
	}
	
	
	public String insertPharmacyEntries(PharmacyEntries pe) {

		per.save(pe);
		return "Description to patient with ID \"" + pe.getId() + "\" has been added.";
	}
	
//////////////////////// MAIL SERVICES /////////////////////////////


	public void sendEmail(MailMessage m) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot. This
		 * JavaMailSender extends the MailSender Interface which contains send()
		 * function. SimpleMailMessage Object is required because send() function uses
		 * object of SimpleMailMessage as a Parameter
		 */

		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(m.getEmailAddress());
		mail.setSubject(m.getSubject());
		mail.setText(m.getBodyText());

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		javaMailSender.send(mail);
	}

	/**
	 * This function is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendEmailWithAttachment(MailMessage m) throws MailException, MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(m.getEmailAddress());
		helper.setSubject(m.getSubject());
		helper.setText(m.getBodyText());

		
		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(message);
	}
}
