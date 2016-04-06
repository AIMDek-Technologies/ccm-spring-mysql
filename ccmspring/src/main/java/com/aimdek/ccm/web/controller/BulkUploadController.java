package com.aimdek.ccm.web.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aimdek.ccm.document.BulkUpload;
import com.aimdek.ccm.dto.DataTableDTO;
import com.aimdek.ccm.service.BulkUploadService;
import com.aimdek.ccm.util.CCMConstant;

/**
 * The Class BulkUploadController.
 *
 * @author aimdek.team
 */
@Controller
@RequestMapping(value = "bulkupload")
public class BulkUploadController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BulkUploadController.class);

	/** The Constant PER_PAGE_OBJECT. */
	public static final int PER_PAGE_OBJECT = 10;

	/** The column name. */
	private String COLUMN_NAME;

	/** The direction. */
	private String DIRECTION;

	/** The initial. */
	private int INITIAL;

	/** The record size. */
	private int RECORD_SIZE;

	/** The uploadedby search term. */
	private String TYPE_SEARCH_TERM, UPLOADEDBY_SEARCH_TERM;

	/** The bulk uploads. */
	private List<BulkUpload> bulkUploads = new ArrayList<BulkUpload>();

	/** The bulk upload service. */
	@Autowired
	private BulkUploadService bulkUploadService;

	/**
	 * Display all uploads.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String displayAllUploads(ModelMap model) {
		model.put("page", "bulkupload");
		return "bulkuploadList";
	}

	/**
	 * Fetch bulk uploads.
	 *
	 * @param request
	 *            the request
	 * @return the data table dto
	 */
	@RequestMapping(value = "filter", method = RequestMethod.GET)
	public @ResponseBody DataTableDTO fetchBulkUploads(HttpServletRequest request) {

		String[] columnNames = { "bulkUploadId", "type", "uploadDate", "totalRecords", "successRecords", "failureRecords", "uploadedBy" };

		int listDisplayAmount = PER_PAGE_OBJECT;
		int start = 0;
		int column = 0;
		String dir = "ASC";
		String pageNo = request.getParameter(CCMConstant.DATATABLE_CONSTANT_IDISPLAYSTART);
		String pageSize = request.getParameter(CCMConstant.DATATABLE_CONSTANT_IDISPLAYLENGTH);
		String colIndex = request.getParameter(CCMConstant.DATATABLE_CONSTANT_ISORTCOL_0);
		String sortDirection = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSORTDIR_0);
		int sEcho = 0;
		if (request.getParameter(CCMConstant.DATATABLE_CONSTANT_SECHO) != null) {
			sEcho = Integer.parseInt(request.getParameter(CCMConstant.DATATABLE_CONSTANT_SECHO));
		}
		if (pageNo != null) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}
		if (pageSize != null) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}
		if (colIndex != null) {
			column = Integer.parseInt(colIndex);
			if (column < 0 || column > 5)
				column = 0;
		}
		if (sortDirection != null && !("asc").equals(sortDirection)) {
			dir = "DESC";
		}

		String colName = columnNames[column];
		int totalRecords = -1;
		totalRecords = (int) bulkUploadService.getBulkUploadsCount(new HashMap<String, Object>());

		TYPE_SEARCH_TERM = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSEARCH_1);
		UPLOADEDBY_SEARCH_TERM = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSEARCH_6);
		COLUMN_NAME = colName;
		DIRECTION = dir;
		INITIAL = start;
		RECORD_SIZE = INITIAL + listDisplayAmount;
		Map<String, Object> map = new HashMap<String, Object>();

		if (TYPE_SEARCH_TERM != null && !"".equals(TYPE_SEARCH_TERM)) {
			map.put(CCMConstant.CONSTANT_TYPE, TYPE_SEARCH_TERM);
		}
		if (UPLOADEDBY_SEARCH_TERM != null && !"".equals(UPLOADEDBY_SEARCH_TERM)) {
			map.put("uploadedBy", UPLOADEDBY_SEARCH_TERM);
		}

		bulkUploads = bulkUploadService.getBulkUploads(INITIAL, RECORD_SIZE, COLUMN_NAME, DIRECTION, map);
		DataTableDTO dataDto = setDataTableDTO(start, sEcho, totalRecords, map);
		return dataDto;
	}

	/**
	 * Sets the data table dto.
	 *
	 * @param start
	 *            the start
	 * @param sEcho
	 *            the s echo
	 * @param totalRecords
	 *            the total records
	 * @param map
	 *            the map
	 * @return the data table dto
	 */
	private DataTableDTO setDataTableDTO(int start, int sEcho, int totalRecords, Map<String, Object> map) {
		DataTableDTO dataDto = new DataTableDTO();
		dataDto.setDraw(start);
		dataDto.setRecordsFiltered((int) bulkUploadService.getBulkUploadsCount(map));
		dataDto.setRecordsTotal(totalRecords);
		dataDto.setData(bulkUploads.toArray());
		dataDto.setsEcho(sEcho);
		return dataDto;
	}

	/**
	 * Open bulk upload customer form.
	 *
	 * @param type
	 *            the type
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "openBulkUploadForm/{type}", method = RequestMethod.GET)
	public String openBulkUploadCustomerForm(@PathVariable String type, ModelMap model) {
		model.put(CCMConstant.CONSTANT_TYPE, type);
		return "bulkUpload";
	}

	/**
	 * Handle file upload.
	 *
	 * @param type
	 *            the type
	 * @param file
	 *            the file
	 * @param modelMap
	 *            the model map
	 * @return the string
	 */
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam(CCMConstant.CONSTANT_TYPE) String type, @RequestParam("file") MultipartFile file, ModelMap modelMap) {
		if (!file.isEmpty()) {
			try {
				User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String fileName = file.getOriginalFilename();
				boolean isUserUpload = false;
				if (type.equalsIgnoreCase(CCMConstant.BULK_UPLOAD_TYPE_CUSTOMER)) {
					isUserUpload = true;
				}
				InputStream inputStream = file.getInputStream();
				String message = bulkUploadService.handleBulkUpload(fileName, file.getContentType(), inputStream, isUserUpload, Long.parseLong(user.getUsername()));
				modelMap.put("message", message);
				modelMap.put("result", "success");
			} catch (Exception e) {
				LOGGER.error("You failed to upload." + e);

			}
		} else {
			LOGGER.error("You failed to upload because the file was empty.");
		}
		return "bulkUpload";
	}

	/**
	 * Gets the bulk uploads.
	 *
	 * @return the bulkUploads
	 */
	public List<BulkUpload> getBulkUploads() {
		return bulkUploads;
	}

	/**
	 * Sets the bulk uploads.
	 *
	 * @param bulkUploads
	 *            the bulkUploads to set
	 */
	public void setBulkUploads(List<BulkUpload> bulkUploads) {
		this.bulkUploads = bulkUploads;
	}

}
