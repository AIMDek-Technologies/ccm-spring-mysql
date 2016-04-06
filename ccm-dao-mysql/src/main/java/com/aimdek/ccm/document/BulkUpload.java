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

package com.aimdek.ccm.document;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA entity BulkUpload.
 *
 * @author aimdek.team
 */
@Entity
@Table(name = "ccm_bulkupload")
public class BulkUpload implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3101585594420428614L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	/** The upload date. */
	@Column(name = "upload_date")
	private Date uploadDate;

	/** The time taken. */
	@Column(name = "time_taken")
	private int timeTaken;

	/** The total records. */
	@Column(name = "total_records")
	private int totalRecords;

	/** The success records. */
	@Column(name = "success_records")
	private int successRecords;

	/** The failure records. */
	@Column(name = "failure_records")
	private int failureRecords;

	/** The file name. */
	@Column(name = "file_name")
	private String fileName;

	/** The error file path. */
	private String errorFilePath;

	/** The type. */
	@Column(name = "type")
	private String type;

	/** The created at. */
	@Column(name = "created_at")
	private final Date createdAt = new Date();

	/** The created by. */
	@Column(name = "created_by")
	private long createdBy;

	/** The uploaded by. */
	@Column(name = "uploaded_by")
	private String uploadedBy;

	/** The bulk upload id. */
	@Column(name = "bulkupload_id")
	private long bulkUploadId;

	/** The pending transactions. */
	@Column(name = "pending_transaction_ids")
	private String pendingTransactions;

	/**
	 * Gets the pending transactions.
	 *
	 * @return the pendingTransactions
	 */
	public String getPendingTransactions() {
		return pendingTransactions;
	}

	/**
	 * Sets the pending transactions.
	 *
	 * @param pendingTransactions
	 *            the pendingTransactions to set
	 */
	public void setPendingTransactions(String pendingTransactions) {
		this.pendingTransactions = pendingTransactions;
	}

	/**
	 * Gets the upload date.
	 *
	 * @return the uploadDate
	 */
	public Date getUploadDate() {
		if (uploadDate != null) {
			return new Date(this.uploadDate.getTime());
		}
		return uploadDate;
	}

	/**
	 * Sets the upload date.
	 *
	 * @param uploadDate
	 *            the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * Gets the time taken.
	 *
	 * @return the timeTaken
	 */
	public int getTimeTaken() {
		return this.timeTaken;
	}

	/**
	 * Sets the time taken.
	 *
	 * @param timeTaken
	 *            the timeTaken to set
	 */
	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	/**
	 * Gets the total records.
	 *
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return this.totalRecords;
	}

	/**
	 * Sets the total records.
	 *
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * Gets the success records.
	 *
	 * @return the successRecords
	 */
	public int getSuccessRecords() {
		return this.successRecords;
	}

	/**
	 * Sets the success records.
	 *
	 * @param successRecords
	 *            the successRecords to set
	 */
	public void setSuccessRecords(int successRecords) {
		this.successRecords = successRecords;
	}

	/**
	 * Gets the failure records.
	 *
	 * @return the failureRecords
	 */
	public int getFailureRecords() {
		return this.failureRecords;
	}

	/**
	 * Sets the failure records.
	 *
	 * @param failureRecords
	 *            the failureRecords to set
	 */
	public void setFailureRecords(int failureRecords) {
		this.failureRecords = failureRecords;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the fileName
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the error file path.
	 *
	 * @return the errorFilePath
	 */
	public String getErrorFilePath() {
		return this.errorFilePath;
	}

	/**
	 * Sets the error file path.
	 *
	 * @param errorFilePath
	 *            the errorFilePath to set
	 */
	public void setErrorFilePath(String errorFilePath) {
		this.errorFilePath = errorFilePath;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		if (createdAt != null) {
			return new Date(this.createdAt.getTime());
		}
		return createdAt;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	public long getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the uploaded by.
	 *
	 * @return the uploadedBy
	 */
	public String getUploadedBy() {
		return this.uploadedBy;
	}

	/**
	 * Sets the uploaded by.
	 *
	 * @param uploadedBy
	 *            the uploadedBy to set
	 */
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	/**
	 * Gets the bulk upload id.
	 *
	 * @return the bulkUploadId
	 */
	public long getBulkUploadId() {
		return this.bulkUploadId;
	}

	/**
	 * Sets the bulk upload id.
	 *
	 * @param bulkUploadId
	 *            the bulkUploadId to set
	 */
	public void setBulkUploadId(long bulkUploadId) {
		this.bulkUploadId = bulkUploadId;
	}
}
