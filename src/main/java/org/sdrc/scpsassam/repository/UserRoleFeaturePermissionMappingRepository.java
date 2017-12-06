/**
 * 
 */
package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.UserRoleFeaturePermissionMapping;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface UserRoleFeaturePermissionMappingRepository {

	<S extends UserRoleFeaturePermissionMapping> List<S> save(Iterable<S> userRoleFeaturePermissionMappings);
	
	public void save(UserRoleFeaturePermissionMapping userRoleFeaturePermissionMapping);
	
}
