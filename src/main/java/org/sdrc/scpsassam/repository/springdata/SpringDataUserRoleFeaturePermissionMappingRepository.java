/**
 * 
 */
package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.UserRoleFeaturePermissionMapping;
import org.sdrc.scpsassam.repository.UserRoleFeaturePermissionMappingRepository;
import org.springframework.data.repository.RepositoryDefinition;


/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=UserRoleFeaturePermissionMapping.class,idClass=Integer.class)
public interface SpringDataUserRoleFeaturePermissionMappingRepository extends
		UserRoleFeaturePermissionMappingRepository {

}
