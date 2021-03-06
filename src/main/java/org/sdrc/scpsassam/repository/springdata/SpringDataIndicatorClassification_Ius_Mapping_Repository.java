package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.IndicatorClassificationIndicatorUnitSubgroupMapping;
import org.sdrc.scpsassam.repository.IndicatorClassification_Ius_Mapping_Repository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=IndicatorClassificationIndicatorUnitSubgroupMapping.class,idClass=Integer.class)
public interface SpringDataIndicatorClassification_Ius_Mapping_Repository extends IndicatorClassification_Ius_Mapping_Repository {

}