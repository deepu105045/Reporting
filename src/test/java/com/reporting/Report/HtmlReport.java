package com.reporting.Report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.reporting.reportingBean.ReportBean;
import com.reporting.reportingBean.RootReport;

public class HtmlReport {
	static final String START_HTML="<html>";
	static final String CLOSE_HTML="</html>";
	static final String START_BODY="<body>";
	static final String CLOSE_BODY="</body>";
	static final String START_TABLE="<table border=1>";
	static final String CLOSE_TABLE="</table>";
	static final String START_ROW="<tr>";
	static final String CLOSE_ROW="</tr>";
	static final String START_COL="<td>";
	static final String CLOSE_COL="</td>";
	private PrintWriter mOut;
	
	public void writeToHtml(RootReport root,String outputDirectory){
		new File(outputDirectory).mkdirs();
		try{
			mOut=new PrintWriter(new BufferedWriter(new FileWriter(new File(outputDirectory, "B-report.html"))));
		}catch(Exception e){
			System.out.println("Error in creating writer "+e);
		}
		startHtml();
		mOut.println("<h1>Test Results</h1>");
		summary("Count",root.getPass(),root.getFail(),root.getSkip());
		failedButNoBugs(root);
		failedWithBugs(root);
		passedResults(root.getPassList(),"PASS");
		passedResults(root.getSkipList(),"SKIPPED");
		endHtml();
		mOut.flush();
		mOut.close();
		
	}
	

	private void passedResults(List<ReportBean> list, String heading) {
		mOut.println("<h2>"+heading+"</h2>");
		mOut.println(START_TABLE);
		for(ReportBean bean:list){	
				testResult(bean.getTestcaseName(),bean.isStatus(), bean.getErrorMsg(), bean.getBugNumber(),bean.getOwner(),bean.getExecutionTime());
		}
		mOut.println(CLOSE_TABLE);
		
	}

	private void failedWithBugs(RootReport root) {
		mOut.println("<h2>FAILED BUGS</h2>");
		mOut.println(START_TABLE);
		for(ReportBean bean:root.getFailList()){	
			if(!bean.getBugNumber().equalsIgnoreCase("NOBUG")){
				testResult(bean.getTestcaseName(),bean.isStatus(), bean.getErrorMsg(), bean.getBugNumber(),bean.getOwner(),bean.getExecutionTime());
			}
		}
		mOut.println(CLOSE_TABLE);
		
	}

	private void failedButNoBugs(RootReport root ){
		mOut.println("<h2>FAILED NOBUGS</h2>");
		mOut.println(START_TABLE);
		for(ReportBean bean:root.getFailList()){	
			if(bean.getBugNumber().equalsIgnoreCase("NOBUG")){
				testResult(bean.getTestcaseName(),bean.isStatus(), bean.getErrorMsg(), bean.getBugNumber(),bean.getOwner(),bean.getExecutionTime());
			}
		}
		mOut.println(CLOSE_TABLE);
		
	}
	
	private void testResult(String testcaseName, boolean status ,String errorMsg, String bugNumber, String owner,String getExecutionTime) {
		mOut.println(START_ROW
								+ START_COL+testcaseName+CLOSE_COL
								+ START_COL+status+CLOSE_COL
								+ START_COL+errorMsg+CLOSE_COL
								+ START_COL+owner+CLOSE_COL
								+ START_COL+bugNumber+CLOSE_COL
					+ CLOSE_ROW);
	}

	

	private void startHtml() {
		mOut.println("<html><body>");		
	}
	
	private void summary(String status,int pass,int fail,int skip){
		mOut.println(START_TABLE
						+ START_ROW
							+ START_COL+"STATUS"+CLOSE_COL
							+ START_COL+status+CLOSE_COL
						+ CLOSE_ROW
						+ START_ROW
							+ START_COL+"PASS"+CLOSE_COL
							+ START_COL+pass+CLOSE_COL
						+ CLOSE_ROW
						+ START_ROW
							+ START_COL+"FAIL"+CLOSE_COL
							+ START_COL+fail+CLOSE_COL
						+ CLOSE_ROW
						+ START_ROW
							+ START_COL+"SKIP"+CLOSE_COL
							+ START_COL+skip+CLOSE_COL
						+ CLOSE_ROW
					+ CLOSE_TABLE);
	}

	
	private void endHtml(){
		mOut.print("</body></html>");
	}


	

}
