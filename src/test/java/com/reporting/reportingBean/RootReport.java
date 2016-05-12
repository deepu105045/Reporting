package com.reporting.reportingBean;

import java.util.List;

public class RootReport {
	private int pass;
	private int fail;
	private int skip;
	private List<ReportBean> passList;
	private List<ReportBean> failList;
	private List<ReportBean> skipList;
	
	
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public List<ReportBean> getPassList() {
		return passList;
	}
	public void setPassList(List<ReportBean> passList) {
		this.passList = passList;
	}
	public List<ReportBean> getFailList() {
		return failList;
	}
	public void setFailList(List<ReportBean> failList) {
		this.failList = failList;
	}
	public List<ReportBean> getSkipList() {
		return skipList;
	}
	public void setSkipList(List<ReportBean> skipList) {
		this.skipList = skipList;
	}

}
