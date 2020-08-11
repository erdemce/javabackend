package com.erdem.enowa.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Baum {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nummer",nullable=false,length =20)
	private long nummer;
	
	@Column(name="pflanzdatum", nullable=false)
	private Date pflanzdatum;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="spezies_id",nullable=false)
	private Spezies spezies;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="baum_id",nullable=false)
	private List<Befund> befunde;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="location_id",nullable=false)
	private GeoLocation location;
}
