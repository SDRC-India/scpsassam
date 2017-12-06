package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.Facility;
import org.sdrc.scpsassam.repository.FacilityRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=Facility.class,idClass=Integer.class)
public interface SpringDataFacilityRepository extends FacilityRepository{

}
