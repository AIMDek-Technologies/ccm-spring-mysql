package com.aimdek.ccm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimdek.ccm.dao.BulkUploadRepositoryCustom;
import com.aimdek.ccm.document.BulkUpload;

/**
 * The Interface BulkUploadRepository.
 * @author aimdek.team
 */
public interface BulkUploadRepository extends JpaRepository<BulkUpload, Long>,BulkUploadRepositoryCustom{

	/**
	 * Find last bulk upload.
	 *
	 * @return BulkUpload
	 */
	public BulkUpload findLastBulkUpload();
	
}
