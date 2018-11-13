package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.domain.Indicator;
import org.sdrc.scpsassam.domain.IndicatorRoleMapping;
import org.sdrc.scpsassam.domain.Role;

public interface IndicatorRoleMappingRepository {
	
	
	public List<IndicatorRoleMapping> findByRole(Role role);
	
	
	//public List<IndicatorRoleMapping> findByRoleAndAgency(Role role,Agency agency);
	
	public List<IndicatorRoleMapping> findByRoleAndAgency(int roleId,int agencyId);
	
	public IndicatorRoleMapping save(IndicatorRoleMapping indicatorRoleMapping);


	public IndicatorRoleMapping findByRoleAndIndicator(Role role, Indicator indicator);

}
