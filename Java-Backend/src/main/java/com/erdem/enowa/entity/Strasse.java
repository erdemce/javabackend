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
public class Strasse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="verwaltungsKuerzel", nullable=false,length=20)
	private String verwaltungsKuerzel;
	
	@Column(name="name", unique = false, nullable=false,length=50)
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="strasse_id",nullable=false)
	private List<Baum> baeume;
	
	
}
