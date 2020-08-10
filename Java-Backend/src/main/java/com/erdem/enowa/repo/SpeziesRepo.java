package com.erdem.enowa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erdem.enowa.entity.Spezies;
import com.erdem.enowa.entity.Strasse;

public interface SpeziesRepo extends JpaRepository<Spezies, Integer>{

}
