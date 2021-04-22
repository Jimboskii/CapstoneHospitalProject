package com.example.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "patient")
public class Patient {
	
	public Patient()
	{
		
	}
	
	@Id 
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long patientId;
	
	@Column(name = "patient_name")
	private String patientName;
	
	//ICU, NEUROLOGY, ORTHOPAEDICS
	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "admitted")
	private String admitted;
	
	
//	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "patient_drugs", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "drug_id"))
	private Set<Drug> Usage = new HashSet<>();

	

	public long getPatientId() {
		return patientId;
	}



	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}



	public String getPatientName() {
		return patientName;
	}



	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public String getAdmitted() {
		return admitted;
	}



	public void setAdmitted(String admitted) {
		this.admitted = admitted;
	}



	public Set<Drug> getUsage() {
		return Usage;
	}



	public void setUsage(Set<Drug> usage) {
		Usage = usage;
	}



	


}
