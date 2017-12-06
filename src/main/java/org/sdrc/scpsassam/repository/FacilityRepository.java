package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.Facility;

public interface FacilityRepository {
	
	
	public List<Facility> findAll();
	
	public Facility save(Facility facility);

}
