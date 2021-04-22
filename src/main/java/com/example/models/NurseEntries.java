package com.example.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "nurse_entries")
public class NurseEntries {

	@Id
	@Column(name = "entry_id")
	private long id;
	
	@Column(name = "description")
	private String description;
	
	
	//@JsonManagedReference
	@ManyToOne
	//@JsonIgnore
	@JoinColumn(name = "patient_id", nullable = false, updatable = false)
	private Patient pat;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Patient getPat() {
		return pat;
	}

	public void setPat(Patient pat) {
		this.pat = pat;
	}


	
	



}
