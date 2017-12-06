package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.Subgroup;
import org.sdrc.scpsassam.repository.SubgroupRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=Subgroup.class,idClass=Integer.class)
public interface SpringDataSubgroupRepository  extends SubgroupRepository{

}
