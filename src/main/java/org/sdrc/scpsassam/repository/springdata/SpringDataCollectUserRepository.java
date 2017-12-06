package org.sdrc.scpsassam.repository.springdata;

import org.sdrc.scpsassam.domain.User;
import org.sdrc.scpsassam.repository.CollectUserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * @author Ratikanta Pradhan (ratikanta@sdrc.co.in)
 * @author Sarita
 * @author Subrata
 * @since version 1.0.0.0
 *
 */
public interface SpringDataCollectUserRepository extends CollectUserRepository, Repository<User, Integer> {

	@Override
	@Query("SELECT user FROM User user WHERE user.userName = :username AND user.password = :password AND user.isActive = True")
	User findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

	User findByUserName(String username);

}
