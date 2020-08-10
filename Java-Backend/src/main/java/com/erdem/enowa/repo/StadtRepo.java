package com.erdem.enowa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.entity.Strasse;

public interface StadtRepo extends JpaRepository<Stadt, Integer>{

}
