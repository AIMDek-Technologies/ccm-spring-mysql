/**
 * 
 */
package com.aimdek.ccm.service;

import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.Geographic;

/**
 * The Interface GeographicService.
 *
 * @author aimdek.team
 */
public interface GeographicService {
	
	/**
	 * Gets the geographics.
	 *
	 * @return the map
	 */
	public Map<String,List<String>> getGeographics();
	
	/**
	 * Find states by country.
	 *
	 * @param country the country
	 * @return the map
	 */
	public Map<String,List<String>> findStatesByCountry(String country);
	
	/**
	 * Find cities by state.
	 *
	 * @param state the state
	 * @return the list
	 */
	public List<String> findCitiesByState(String state);
	
	/**
	 * Find geographics by zip.
	 *
	 * @param zip the zip
	 * @return the geographic
	 */
	public Geographic findGeographicsByZip(long zip);
	
	/**
	 * Find geographics by id.
	 *
	 * @param geoId the geo id
	 * @return the geographic
	 */
	public Geographic findGeographicsById(long geoId);
	
	/**
	 * Save geographic.
	 *
	 * @param geographic the geographic
	 */
	public void saveGeographic(Geographic geographic);
	
}
