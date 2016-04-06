/**
 * 
 */
package com.aimdek.ccm.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The Class DataTableDTO.
 *
 * @author aimdek.team
 */
public class DataTableDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6265565117937539517L;

	/** The records filtered. */
	private long recordsFiltered;

	/** The records total. */
	private long recordsTotal;

	/** The data. */
	private Object[] data;

	/** The s echo. */
	private int sEcho;

	/** The draw. */
	private int draw;

	/**
	 * Gets the records filtered.
	 *
	 * @return the recordsFiltered
	 */
	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * Sets the records filtered.
	 *
	 * @param recordsFiltered
	 *            the recordsFiltered to set
	 */
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * Gets the records total.
	 *
	 * @return the recordsTotal
	 */
	public long getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * Sets the records total.
	 *
	 * @param recordsTotal
	 *            the recordsTotal to set
	 */
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object[] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the data to set
	 */
	public void setData(Object[] data) {
		if (data == null) {
			this.data = new Object[0];
		} else {
			this.data = Arrays.copyOf(data, data.length);
		}
	}

	/**
	 * Gets the s echo.
	 *
	 * @return the sEcho
	 */
	public int getsEcho() {
		return sEcho;
	}

	/**
	 * Sets the s echo.
	 *
	 * @param sEcho2
	 *            the sEcho to set
	 */
	public void setsEcho(int sEcho2) {
		this.sEcho = sEcho2;
	}

	/**
	 * Gets the draw.
	 *
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}

	/**
	 * Sets the draw.
	 *
	 * @param draw
	 *            the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}
}
