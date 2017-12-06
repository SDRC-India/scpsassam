package org.sdrc.scpsassam.repository.springdata;

import javax.persistence.LockModeType;

import org.sdrc.scpsassam.domain.User;
import org.sdrc.scpsassam.repository.UserRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;


@RepositoryDefinition(domainClass=User.class,idClass=Integer.class)
public interface SpringDataUserRepository extends UserRepository{

	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select u from User u where u.userId =:userId")
	public User findByUserIdWithLockedInstance(@Param("userId")Integer userId);
}
