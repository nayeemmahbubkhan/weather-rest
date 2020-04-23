package de.weather.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.weather.domainobject.Blacklist;
import de.weather.domainobject.User;

/**
 * Database Access Object for blacklist table.
 * <p/>
 */
public interface BlacklistRepository extends PagingAndSortingRepository<Blacklist, Long> {
		
	User findBlacklistByToken(String username);

}
