package com.aimdek.ccm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimdek.ccm.dao.UserRepositoryCustom;
import com.aimdek.ccm.document.User;

/**
 * The Interface UserRepository.
 * 
 * @author aimdek.team
 * 
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the user
	 */
	public User findByEmail(String email);

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the user
	 */
	public User findById(long id);

}
