package com.pc.encurtalinks.domain.links;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepository extends JpaRepository<Links, String>{
	
}
