package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.IndicatorUnitSubgroup;
import org.sdrc.scpsassam.repository.IndicatorUnitSubgroupRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=IndicatorUnitSubgroup.class,idClass=Integer.class)
public interface SpringDataIndicatorUnitSubgroupRepository extends IndicatorUnitSubgroupRepository{
	
	

}
