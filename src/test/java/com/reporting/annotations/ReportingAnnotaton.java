package com.reporting.annotations;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.testng.annotations.Test;


	@Target({ElementType.TYPE,ElementType.METHOD}) 
	@Retention(RetentionPolicy.RUNTIME) 
	@Documented 
	@Inherited
	public @interface ReportingAnnotaton {
		public String owner() default "default"; //test case owner
		public String bugNumber() default "NOBUG";//Bug number if its known issues
		
	}

