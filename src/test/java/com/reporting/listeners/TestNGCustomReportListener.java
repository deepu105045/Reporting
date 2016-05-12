package com.reporting.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.reporting.Report.HtmlReport;
import com.reporting.annotations.ReportingAnnotaton;
import com.reporting.reportingBean.ReportBean;
import com.reporting.reportingBean.RootReport;

public class TestNGCustomReportListener implements IReporter {
	private int passTestCount = 0, failTestCount = 0, skipTestCount = 0;
	RootReport root = new RootReport();
	List<ReportBean> failReporterList = new ArrayList<ReportBean>();
	List<ReportBean> passReporterList = new ArrayList<ReportBean>();
	List<ReportBean> skipReporterList = new ArrayList<ReportBean>();

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (String testName : suiteResults.keySet()) {
				ISuiteResult suiteResult = suiteResults.get(testName);
				ITestContext testContext = suiteResult.getTestContext();

				passTestCount = passTestCount + testContext.getPassedTests().size();
				failTestCount = failTestCount + testContext.getFailedTests().size();
				skipTestCount = skipTestCount + testContext.getSkippedTests().size();

				IResultMap failedResult = testContext.getFailedTests();
				IResultMap passResult = testContext.getPassedTests();
				IResultMap skipResult = testContext.getSkippedTests();

				
				assignFailedResultsToReportinBean(failedResult);
				assignPassResultsToReportinBean(passResult);
				assignSkipResultsToReportinBean(skipResult);

			}

			root.setFail(failTestCount);
			root.setPass(passTestCount);
			root.setSkip(skipTestCount);
			root.setFailList(failReporterList);
			root.setPassList(passReporterList);
			root.setSkipList(skipReporterList);
			HtmlReport htmlReport = new HtmlReport();
			htmlReport.writeToHtml(root, outputDirectory);

		}

	}

	private void assignFailedResultsToReportinBean(IResultMap failedResult) {
		Set<ITestResult> testsFailed = failedResult.getAllResults();
		for (ITestResult testResult : testsFailed) {
			ReportBean reportBean = new ReportBean();
			reportBean.setTestcaseName(testResult.getName());
			reportBean.setExecutionTime((testResult.getEndMillis() - testResult.getStartMillis() + " ms"));
			reportBean.setBugNumber(getBugNumber(testResult));
			reportBean.setOwner(getOwner(testResult));
			reportBean.setErrorMsg(testResult.getThrowable().toString());
			reportBean.setStatus(Boolean.FALSE);
			failReporterList.add(reportBean);			
			reportBean = null;
		}

	}

	private void assignPassResultsToReportinBean(IResultMap passResult) {
		Set<ITestResult> testsPassed = passResult.getAllResults();
		for (ITestResult testResult : testsPassed) {
			ReportBean reportBean = new ReportBean();
			reportBean.setTestcaseName(testResult.getName());
			reportBean.setExecutionTime((testResult.getEndMillis() - testResult.getStartMillis() + " ms"));
			reportBean.setBugNumber(getBugNumber(testResult));
			reportBean.setOwner(getOwner(testResult));
			reportBean.setStatus(Boolean.TRUE);
			passReporterList.add(reportBean);
			reportBean = null;
		}

	}

	private void assignSkipResultsToReportinBean(IResultMap passResult) {
		Set<ITestResult> testsPassed = passResult.getAllResults();
		for (ITestResult testResult : testsPassed) {
			ReportBean reportBean = new ReportBean();
			reportBean.setTestcaseName(testResult.getName());
			reportBean.setExecutionTime((testResult.getEndMillis() - testResult.getStartMillis() + " ms"));
			reportBean.setBugNumber(getBugNumber(testResult));
			reportBean.setOwner(getOwner(testResult));
			reportBean.setStatus(Boolean.TRUE);
			skipReporterList.add(reportBean);
			reportBean = null;
		}

	}

	private String getBugNumber(ITestResult testResult) {
		String bugNumber = "NOBUG";
		try {
			bugNumber = testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(ReportingAnnotaton.class).bugNumber();
					
		} catch (Exception e) {
			bugNumber = "NOBUG";
		}
		return bugNumber;
	}

	private String getOwner(ITestResult testResult) {
		String owner = "default";
		try {
			owner = testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(ReportingAnnotaton.class).owner();
					
		} catch (Exception e) {
			owner = "default";
		}
		return owner;
	}

}
