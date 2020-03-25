package de.weather.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.weather.domainobject.User;

/**
 * Database Access Object for operation table.
 * <p/>
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
		
	Page<User> findAll(Pageable pageable);
	
	User findUserByUsername(String username);

}
