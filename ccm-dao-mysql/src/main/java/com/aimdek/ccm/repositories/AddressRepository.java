package com.aimdek.ccm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimdek.ccm.dao.AddressRepositoryCustom;
import com.aimdek.ccm.document.Address;

/**
 * The Interface AddressRepository.
 * @author aimdek.team
 */
public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {

	/**
	 * Find by user id.
	 *
	 * @param userId the user id
	 * @return the address
	 */
	public Address findByUserId(long userId);
}
