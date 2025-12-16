package com.savelife;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepoImpl extends JpaRepository<Donor, Long>{

	Donor findByEmailAndPassword(String mail, String password);
	
	Donor findByEmail(String mail);

}
