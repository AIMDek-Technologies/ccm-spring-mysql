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

import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.BulkUpload;

/**
 * The Interface BulkUploadDao.
 *
 * @author aimdek.team
 */
public interface BulkUploadRepositoryCustom extends GenericDao<BulkUpload, String> {
	
	/**
	 * Save bulk upload.
	 *
	 * @param bulkUpload the bulk upload
	 */
	public void saveBulkUpload(BulkUpload bulkUpload);

	/**
	 * Find last bulk upload.
	 *
	 * @return the bulk upload
	 */
	public BulkUpload findLastBulkUpload();

	
	/**
	 * Gets the bulk uploads.
	 *
	 * @param start the start
	 * @param end the end
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @return the list of bulk upload
	 */
	public List<BulkUpload> getBulkUploads(int start, int limit, String sortField, String sortOrder, Map<String, Object> filters);

	/**
	 * Gets the bulk uploads count.
	 *
	 * @param filters the filters
	 * @return the bulk uploads count
	 */
	long getBulkUploadsCount(Map<String, Object> filters);

}
