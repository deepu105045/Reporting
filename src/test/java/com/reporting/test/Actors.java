package com.reporting.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.reporting.annotations.ReportingAnnotaton;


public class Actors {
	
	@ReportingAnnotaton(owner="Mohanlal")
	@Test()
	public void mohanlal(){
		Assert.assertEquals("CompleteActor", "CompleteActor","Mohanlal is real star");
	}
	
	@ReportingAnnotaton(owner="Mamootty")
	@Test()
	public void mamootty(){
		Assert.assertEquals("Superstar", "Average","Mamootty is not a superstar");
	}

}
