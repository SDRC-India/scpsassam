package org.sdrc.scpsassam.repository.springdata;

import java.util.List;

import org.sdrc.scpsassam.domain.IndicatorRoleMapping;
import org.sdrc.scpsassam.repository.IndicatorRoleMappingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

@RepositoryDefinition(domainClass = IndicatorRoleMapping.class, idClass = Integer.class)
public interface SpringDataIndicatorRoleMappingRepository extends IndicatorRoleMappingRepository {

	
	@Query("SELECT i FROM IndicatorRoleMapping i  JOIN i.indicator as indi  WHERE indi.agency.agencyId = :agencyId and i.role.roleId = :roleId")
	public List<IndicatorRoleMapping> findByRoleAndAgency(@Param(value = "roleId") int roleId, @Param(value = "agencyId") int agencyId);

	
	
}
