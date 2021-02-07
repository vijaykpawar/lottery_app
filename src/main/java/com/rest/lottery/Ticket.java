package com.rest.lottery;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class Ticket.
 */
public class Ticket {

	/** The id. */
	private long id;
	
	/** The lines. */
	private List<Line> lines ;
	
	/** The status. */
	@JsonIgnore
	private List<Integer> status;
	
	/** The is status checked. */
	private boolean isStatusChecked=false;
	
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
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Gets the lines.
	 *
	 * @return the lines
	 */
	public List<Line> getLines() {
		return lines;
	}
	
	/**
	 * Sets the lines.
	 *
	 * @param lines the new lines
	 */
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public List<Integer> getStatus() {
		status=new ArrayList<Integer>();
		for (Line line : lines) {
			status.add(line.getResult());
		}
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(List<Integer> status) {
		this.status = status;
	}
	
	/**
	 * Checks if is status checked.
	 *
	 * @return true, if is status checked
	 */
	public boolean isStatusChecked() {
		return isStatusChecked;
	}
	
	/**
	 * Sets the status checked.
	 *
	 * @param isStatusChecked the new status checked
	 */
	public void setStatusChecked(boolean isStatusChecked) {
		this.isStatusChecked = isStatusChecked;
	}
	
	

}
