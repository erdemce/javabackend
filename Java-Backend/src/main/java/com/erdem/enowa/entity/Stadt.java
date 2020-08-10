package com.erdem.enowa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stadt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="plz", unique = true, nullable=false)
	private String plz;
	
	@Column(name="name", unique = true, nullable=false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="stadt_id")
	private List<Strasse> strassen;
	
}
