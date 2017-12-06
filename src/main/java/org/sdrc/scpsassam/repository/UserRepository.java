package org.sdrc.scpsassam.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.sdrc.scpsassam.domain.User;
import org.springframework.data.jpa.repository.Lock;

public interface UserRepository {
	
	
	public User save(User user);
	
	
	public User findByUserId(int userId);
	
	
	public User findByUserNameAndPassword(String username,String password);
	
	
	public List<User> findAll();


	public User findByUserName(String username);

	public User findByUserIdWithLockedInstance(Integer userId);
	


}
