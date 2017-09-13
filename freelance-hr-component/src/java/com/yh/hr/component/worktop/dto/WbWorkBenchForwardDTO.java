package com.yh.hr.component.worktop.dto;

/**
 * 工作台dto
 * @author huw
 * @time 2016-09-29
 */
public class WbWorkBenchForwardDTO 
{
	private Long workBenchOid;
	private String workBenchPanel;
	private String workBenchTbar;
	private String workBenchForm;
	private String workBenchGrid;
	private String workBenchUrl;
	public Long getWorkBenchOid() {
		return workBenchOid;
	}
	public void setWorkBenchOid(Long workBenchOid) {
		this.workBenchOid = workBenchOid;
	}
	public String getWorkBenchForm() {
		return workBenchForm;
	}
	public void setWorkBenchForm(String workBenchForm) {
		this.workBenchForm = workBenchForm;
	}
	public String getWorkBenchGrid() {
		return workBenchGrid;
	}
	public void setWorkBenchGrid(String workBenchGrid) {
		this.workBenchGrid = workBenchGrid;
	}
	public String getWorkBenchUrl() {
		return workBenchUrl;
	}
	public void setWorkBenchUrl(String workBenchUrl) {
		this.workBenchUrl = workBenchUrl;
	}
	public void setWorkBenchPanel(String workBenchPanel) {
		this.workBenchPanel = workBenchPanel;
	}
	public String getWorkBenchPanel() {
		return workBenchPanel;
	}
	public void setWorkBenchTbar(String workBenchTbar) {
		this.workBenchTbar = workBenchTbar;
	}
	public String getWorkBenchTbar() {
		return workBenchTbar;
	}
}
