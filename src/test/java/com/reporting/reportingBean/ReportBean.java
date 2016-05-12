package com.reporting.reportingBean;

public class ReportBean {
	private String testcaseName;
	private String executionTime;
	private String errorMsg;
	private String owner;
	private String bugNumber;
	private boolean status;
	public String getTestcaseName() {
		return testcaseName;
	}
	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}
	public String getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getBugNumber() {
		return bugNumber;
	}
	public void setBugNumber(String bugNumber) {
		this.bugNumber = bugNumber;
	}
	
	

}
