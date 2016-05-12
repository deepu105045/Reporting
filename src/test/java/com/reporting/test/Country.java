package com.reporting.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.reporting.annotations.ReportingAnnotaton;



public class Country {
	
	@ReportingAnnotaton(owner="Deepu",bugNumber="PHX-6531")
	@Test(description="India Test description")
	public void India(){
		throw null;
		//Assert.assertTrue(false,"India test Failed");
	}

	@ReportingAnnotaton(owner="Deepu")
	@Test()
	public void Bangladesh(){
		Assert.assertTrue(false,"US test Failed");
	}
	
	
}
