package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.FacilityUserMapping;
import org.sdrc.scpsassam.repository.FacilityUserMappingRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=FacilityUserMapping.class,idClass=Integer.class)
public interface SpringDataFacilityUserMappingRepository extends FacilityUserMappingRepository{

}
