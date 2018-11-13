package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.Role;

public interface RoleRepository {
	
	
	public List<Role> findAll();
	
	
	public List<Role> findByWillAppearAsRoleInIndicatorMgmtIsTrue();


	public Role findByRoleName(String rollName);


	public List<Role> findAllByRoleNameIn(List<String> rols);


	public Role findByRoleId(int roleId);


	public Role findByRoleCode(String name);

}
