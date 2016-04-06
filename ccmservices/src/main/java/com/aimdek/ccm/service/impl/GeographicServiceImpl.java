/**
 * 
 */
package com.aimdek.ccm.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.repositories.GeographicRepository;
import com.aimdek.ccm.service.GeographicService;
import com.aimdek.ccm.util.CCMConstant;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class GeographicServiceImpl.
 *
 * @author aimdek.team
 */
@Service("geographicService")
public class GeographicServiceImpl implements GeographicService, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -268653960054815644L;

	/** The geographic repository. */
	@Autowired
	private GeographicRepository geographicRepository;

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> getGeographics() {

		List<Geographic> geoList = (List<Geographic>) geographicRepository.findAll();

		Map<String, List<String>> masterGeoList = populateCountryStateCityMap(geoList, 1);

		return masterGeoList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> findStatesByCountry(String country) {

		List<Geographic> geoList = geographicRepository.findByCountry(country);

		Map<String, List<String>> masterList = populateCountryStateCityMap(geoList, 2);

		return masterList;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> findCitiesByState(String state) {

		List<Geographic> geoList = geographicRepository.findByState(state);

		Map<String, List<String>> masterList = populateCountryStateCityMap(geoList, 3);

		return masterList.get(CCMConstant.MASTER_CITY);
	}

	/**
	 * {@inheritDoc}
	 */
	public Geographic findGeographicsByZip(long zipCode) {

		Geographic geographic = geographicRepository.findByZipcode(zipCode);

		return geographic;
	}

	/**
	 * Populate country state city map.
	 *
	 * @param geoList the geo list
	 * @param level the level
	 * @return the map
	 */
	private Map<String, List<String>> populateCountryStateCityMap(List<Geographic> geoList, int level) {
		Map<String, List<String>> masterGeoList = new HashMap<String, List<String>>();

		List<String> countryList = new ArrayList<String>();
		List<String> stateList = new ArrayList<String>();
		List<String> cityList = new ArrayList<String>();

		if (!geoList.isEmpty()) {
			for (Geographic geographic : geoList) {
				if (!countryList.contains(geographic.getCountry())) {

					countryList.add(geographic.getCountry());
				}
				if (!stateList.contains(geographic.getState())) {

					stateList.add(geographic.getState());
				}
				if (!cityList.contains(geographic.getCity())) {

					cityList.add(geographic.getCity());
				}
			}
			// Sort the list.
			Collections.sort(countryList);
			Collections.sort(stateList);
			Collections.sort(cityList);
			if (level == 1) {
				masterGeoList.put(CCMConstant.MASTER_COUNTRY, countryList);
				masterGeoList.put(CCMConstant.MASTER_STATE, stateList);
				masterGeoList.put(CCMConstant.MASTER_CITY, cityList);
			} else if (level == 2) {
				masterGeoList.put(CCMConstant.MASTER_STATE, stateList);
				masterGeoList.put(CCMConstant.MASTER_CITY, cityList);
			}

			else if (level == 3) {
				masterGeoList.put(CCMConstant.MASTER_CITY, cityList);
			}
		}
		return masterGeoList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Geographic findGeographicsById(long id) {
		Geographic geographic = geographicRepository.findById(id);
		if (CommonUtil.isNull(geographic)) {
			geographic = new Geographic();
		}
		return geographic;
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveGeographic(Geographic geographic) {
		if (CommonUtil.isNotNull(geographic)) {
			geographicRepository.saveGeographic(geographic);
		}
	}
}
