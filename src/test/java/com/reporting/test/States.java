package com.reporting.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.reporting.annotations.ReportingAnnotaton;

public class States {
	
	@ReportingAnnotaton(owner="Kamal",bugNumber="PHX-6511")
	@Test(description="kerala")
	public void kerala(){
		Assert.assertTrue(false,"Kerala test failed");
	}
	
	@ReportingAnnotaton(owner="Kamal",bugNumber="PHX-301")
	@Test(description="desc for Tamil Nadu")
	public void tamilNadu(){
		Assert.assertTrue(false,"Tamilnadu test failed");
	}
	
	@ReportingAnnotaton(owner="Kamal",bugNumber="PHX-501")
	@Test()
	public void Delhi(){
		Assert.assertTrue(false,"Delhi test failed");
	}
	
}


