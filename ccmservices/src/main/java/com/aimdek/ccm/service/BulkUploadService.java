/**
 * 
 */
package com.aimdek.ccm.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.BulkUpload;

/**
 * The Interface BulkUploadService.
 *
 * @author aimdek.team
 */
public interface BulkUploadService {
	
	/**
	 * Handle bulk upload.
	 *
	 * @param fileName the file name
	 * @param contentType the content type
	 * @param inputStream the input stream
	 * @param isUserUpload the is user upload
	 * @param userId the user id
	 * @return the string
	 */
	public String handleBulkUpload(String fileName, String contentType, InputStream inputStream, boolean isUserUpload, long userId);
	
	/**
	 * Next bulk upload id.
	 *
	 * @return the long
	 */
	public long nextBulkUploadId();
	
	/**
	 * Gets the bulk uploads.
	 *
	 * @param start the start
	 * @param end the end
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @return the bulk uploads
	 */
	public List<BulkUpload> getBulkUploads(int start, int end, String sortField, String sortOrder, Map<String, Object> filters);
	
	/**
	 * Gets the bulk uploads count.
	 *
	 * @param filters the filters
	 * @return the bulk uploads count
	 */
	public long getBulkUploadsCount(Map<String, Object> filters);

}
