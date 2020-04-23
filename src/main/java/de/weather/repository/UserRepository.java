package de.weather.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.weather.domainobject.User;

/**
 * Database Access Object for user table.
 * <p/>
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
			
	User findUserByUsername(String username);

}
