package com.pc.encurtalinks.domain.links;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepository extends JpaRepository<Links, String>{
	
	@Query(value = "INSERT INTO links (link_encurtado, link_original) VALUES (:link_encurtado, :link_original)", nativeQuery = true)
	@Modifying
	@Transactional
	void saveNew(@Param("link_encurtado") String linkEncurtado, @Param("link_original") String linkOriginal);
	
	Optional<Links> findByLinkEncurtado(String linkEncurtado);
}
