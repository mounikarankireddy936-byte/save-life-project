package com.savelife;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRequestDao extends JpaRepository<ContactRequest, Long>{

	List<ContactRequest> findByIsActiveTrue();

}
