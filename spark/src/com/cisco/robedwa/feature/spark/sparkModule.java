package com.cisco.robedwa.feature.spark;

import org.apache.log4j.Logger;

import com.cloupia.service.cIM.inframgr.AbstractCloupiaModule;
import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.CustomFeatureRegistry;
import com.cloupia.service.cIM.inframgr.collector.controller.CollectorFactory;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReport;


public class sparkModule extends AbstractCloupiaModule {
	
	private static Logger logger = Logger.getLogger(sparkModule.class);

	@Override
	public CollectorFactory[] getCollectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CloupiaReport[] getReports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractTask[] getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(CustomFeatureRegistry arg0) {
		// TODO Auto-generated method stub
		
	}

}
