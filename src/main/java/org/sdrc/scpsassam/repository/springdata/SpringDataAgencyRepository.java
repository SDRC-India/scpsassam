package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.repository.AgencyRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=Agency.class,idClass=Integer.class)
public interface SpringDataAgencyRepository extends AgencyRepository {

}
