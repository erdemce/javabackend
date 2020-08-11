package com.erdem.enowa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erdem.enowa.entity.Strasse;

public interface StrasseRepo extends JpaRepository<Strasse, Integer>{
	
	/*@Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
		    countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
		    nativeQuery = true)
		  save (String lastname, Pageable pageable);
		}*/

}
