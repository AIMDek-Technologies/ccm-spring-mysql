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

package com.aimdek.ccm.dao;

import com.aimdek.ccm.document.Geographic;

/**
 * The Interface GeographicDao.
 *
 * @author aimdek.team
 */
public interface GeographicRepositoryCustom extends GenericDao<Geographic, String> {


	/**
	 * Save geographic.
	 *
	 * @param geographic the geographic
	 */
	public void saveGeographic(Geographic geographic);
}
