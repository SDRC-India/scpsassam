package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.RoleFeaturePermissionScheme;
import org.sdrc.scpsassam.repository.RoleFeaturePermissionRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=RoleFeaturePermissionScheme.class,idClass=Integer.class)
public interface SpringDataRoleFeaturePermissionRepository extends RoleFeaturePermissionRepository{

}
