package com.erdem.enowa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spezies {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="botanischerName", nullable=false,length =60)
	private String botanischerName;
	
	@Column(name="trivialName", nullable=false, length =40)
	private String trivialName;
	
}
