package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.Unit;
import org.sdrc.scpsassam.repository.UnitRepository;
import org.springframework.data.repository.RepositoryDefinition;



@RepositoryDefinition(domainClass=Unit.class,idClass=Integer.class)
public interface SpringDataUnitRepository extends UnitRepository{

}
