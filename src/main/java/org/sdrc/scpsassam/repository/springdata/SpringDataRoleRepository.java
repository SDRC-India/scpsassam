package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.Role;
import org.sdrc.scpsassam.repository.RoleRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=Role.class,idClass=Integer.class)
public interface SpringDataRoleRepository extends RoleRepository{

}
