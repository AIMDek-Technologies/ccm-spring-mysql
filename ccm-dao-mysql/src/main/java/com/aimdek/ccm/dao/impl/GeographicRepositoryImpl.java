/*
 * Copyright (c) 2014-2015 AIMDek Technologies Private Limited. All Rights Reserved.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     AIMDek Technologies Private Limited - initial API and implementation
 */

package com.aimdek.ccm.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.dao.GeographicRepositoryCustom;
import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.repositories.GeographicRepository;

/**
 * The Class GeographicDaoImpl provides implementation of the interface
 * GeographicDao.
 *
 * @author aimdek.team
 */
@Transactional 
public class GeographicRepositoryImpl extends BasicAbstractGenericDaoImpl<Geographic, String> implements GeographicRepositoryCustom {

	
	/** The geographic repository. */
	@Autowired
	private GeographicRepository geographicRepository;

	/**
	 * {@inheritDoc}
	 */
	public void saveGeographic(Geographic geographic) {
		geographicRepository.save(geographic);
	}
}
