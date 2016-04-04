package com.cloupia.feature.spark.tasks;

import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.AbstractScheduleTask;

public class DummyScheduleTask extends AbstractScheduleTask {

	public DummyScheduleTask(String moduleID) {
		super(moduleID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(long arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public long getFrequency() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FormLOVPair[] getFrequencyHoursLov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormLOVPair[] getFrequencyMinsLov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getScheduleTaskName() {
		// TODO Auto-generated method stub
		return null;
	}

}
