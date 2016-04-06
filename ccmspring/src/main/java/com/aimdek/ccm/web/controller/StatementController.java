package com.aimdek.ccm.web.controller;

import static com.aimdek.ccm.util.CCMConstant.DOWNLOAD_STATEMENT_NAME;
import static com.aimdek.ccm.util.CCMConstant.PDF_FILE_EXTENTION;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aimdek.ccm.document.Statement;
import com.aimdek.ccm.dto.DataTableDTO;
import com.aimdek.ccm.service.StatementService;
import com.aimdek.ccm.util.CCMConstant;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class StatementController.
 *
 * @author aimdek.team
 */
@Controller
@RequestMapping(value = "statement")
public class StatementController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(StatementController.class);

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

	/** The cardnumber search term. */
	private String CARDNUMBER_SEARCH_TERM;

	/** The statements. */
	private List<Statement> statements = new ArrayList<Statement>();

	/** Size of a byte buffer to read/write file. */
	private static final int BUFFER_SIZE = 4096;

	/** The statement service. */
	@Autowired
	private StatementService statementService;

	/**
	 * Display all statements.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String displayAllStatements(ModelMap model) {
		model.put("page", "statement");
		return "statementList";
	}

	/**
	 * Fetch statements.
	 *
	 * @param request
	 *            the request
	 * @return the data table dto
	 */
	@RequestMapping(value = "filter", method = RequestMethod.GET)
	public @ResponseBody DataTableDTO fetchStatements(HttpServletRequest request) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String[] columnNames = { "statementId", "statementDate", "cardNumber", "fromDate", "toDate", "amountDue", "dueDate" };
		int listDisplayAmount = PER_PAGE_OBJECT;
		int start = 0;
		int column = 0;
		String dir = "ASC";
		String pageNo = request.getParameter(CCMConstant.DATATABLE_CONSTANT_IDISPLAYSTART);
		String pageSize = request.getParameter(CCMConstant.DATATABLE_CONSTANT_IDISPLAYLENGTH);
		String colIndex = request.getParameter(CCMConstant.DATATABLE_CONSTANT_ISORTCOL_0);
		String sortDirection = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSORTDIR_0);
		int sEcho = 0;
		if (CommonUtil.isNotNull(request.getParameter(CCMConstant.DATATABLE_CONSTANT_SECHO))) {
			sEcho = Integer.parseInt(request.getParameter(CCMConstant.DATATABLE_CONSTANT_SECHO));
		}
		if (CommonUtil.isNotNull(pageNo)) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}
		if (CommonUtil.isNotNull(pageSize)) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}
		if (CommonUtil.isNotNull(colIndex)) {
			column = Integer.parseInt(colIndex);
			if (column < 0 || column > 5)
				column = 0;
		}
		if (CommonUtil.isNotNull(sortDirection) && !("asc").equals(sortDirection)) {
			dir = "DESC";
		}

		String colName = columnNames[column];
		int totalRecords = -1;
		totalRecords = (int) statementService.getStatementsCount(new HashMap<String, Object>(), Long.parseLong(user.getUsername()));

		CARDNUMBER_SEARCH_TERM = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSEARCH_2);
		COLUMN_NAME = colName;
		DIRECTION = dir;
		INITIAL = start;
		RECORD_SIZE = INITIAL + listDisplayAmount;
		Map<String, Object> map = new HashMap<String, Object>();

		if (CommonUtil.isNotNull(CARDNUMBER_SEARCH_TERM)) {
			map.put(CCMConstant.FIELDCONSTANT_CARDNUMBER, CARDNUMBER_SEARCH_TERM);
		}

		statements = statementService.getStatements(INITIAL, RECORD_SIZE, COLUMN_NAME, DIRECTION, map, Long.parseLong(user.getUsername()));

		DataTableDTO dataDto = setDataTableDTO(start, sEcho, totalRecords, map, Long.parseLong(user.getUsername()));
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
	private DataTableDTO setDataTableDTO(int start, int sEcho, int totalRecords, Map<String, Object> map, long userId) {
		DataTableDTO dataDto = new DataTableDTO();
		dataDto.setDraw(start);
		dataDto.setRecordsFiltered((int) statementService.getStatementsCount(map, userId));
		dataDto.setRecordsTotal(totalRecords);
		dataDto.setData(statements.toArray());
		dataDto.setsEcho(sEcho);
		return dataDto;
	}

	/**
	 * Downloading Statement.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param statementId
	 *            the statement id
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = "download/{statementId}", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable long statementId) throws IOException {

		Statement statement = statementService.findStatementByStatementId(statementId);

		if (CommonUtil.isNotNull(statement)) {
			String fullPath = statementService.getAssignedUrl(statement.getDocumentReference());

			if (CommonUtil.isNotNull(fullPath)) {
				InputStream input = null;
				File file = new File(DOWNLOAD_STATEMENT_NAME + PDF_FILE_EXTENTION);
				try {
					URL url = new URL(fullPath);

					FileUtils.copyURLToFile(url, file);
					if (CommonUtil.isNotNull(file) && file.length() > 0) {
						input = url.openStream();
					}
				} catch (MalformedURLException e) {
					LOGGER.error(e);
				} catch (IOException e) {
					LOGGER.error(e);
				}

				// set content attributes for the response
				response.setContentType(CCMConstant.PDF_CONTENT_TYPE);
				

				// set headers for the response
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
				response.setHeader(headerKey, headerValue);

				// get output stream of the response
				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;

				// write bytes read from the input stream into the output stream
				if (CommonUtil.isNotNull(input)) {
					while ((bytesRead = input.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
				}

				input.close();
				outStream.close();
			}

		}
	}

	/**
	 * Gets the statements.
	 *
	 * @return the statements
	 */
	public List<Statement> getStatements() {
		return statements;
	}

	/**
	 * Sets the statements.
	 *
	 * @param statements
	 *            the statements to set
	 */
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
}
