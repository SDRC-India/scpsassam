package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.Role;
import org.sdrc.scpsassam.domain.RoleFeaturePermissionScheme;

public interface RoleFeaturePermissionRepository {
	
	
	public List<RoleFeaturePermissionScheme> findAll();

	public List<RoleFeaturePermissionScheme> findByRole(Role role);

}
