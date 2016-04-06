package com.aimdek.ccm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimdek.ccm.dao.GeographicRepositoryCustom;
import com.aimdek.ccm.document.Geographic;

/**
 * The Interface GeographicRepository.
 * 
 * @author aimdek.team
 */
public interface GeographicRepository extends JpaRepository<Geographic, Long>, GeographicRepositoryCustom {

	/**
	 * Find by country.
	 *
	 * @param country
	 *            the country
	 * @return the list
	 */
	public List<Geographic> findByCountry(String country);

	/**
	 * Find by state.
	 *
	 * @param state
	 *            the state
	 * @return the list
	 */
	public List<Geographic> findByState(String state);

	/**
	 * Find by zipcode.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the geographic
	 */
	public Geographic findByZipcode(long zipcode);

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the geographic
	 */
	public Geographic findById(long id);
}
