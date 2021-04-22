package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drugs")
public class Drug {
	
	public Drug()
	{
		
	}
	
	
	@Id @Column(name = "drug_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private DrugTypes drugType;
	
	@Column(name = "drug_name")
	private String drugName;
	
	@Column(name = "drug_description")
	private String drugDesc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDrugDesc() {
		return drugDesc;
	}

	public void setDrugDesc(String drugDesc) {
		this.drugDesc = drugDesc;
	}

	public DrugTypes getDrugType() {
		return drugType;
	}

	public void setDrugType(DrugTypes drugType) {
		this.drugType = drugType;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	

}
