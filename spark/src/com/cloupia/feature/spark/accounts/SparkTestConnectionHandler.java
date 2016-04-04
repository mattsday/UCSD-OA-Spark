package com.cloupia.feature.spark.accounts;

import com.cloupia.lib.connector.account.PhysicalConnectivityStatus;
import com.cloupia.lib.connector.account.PhysicalConnectivityTestHandler;

public class SparkTestConnectionHandler extends PhysicalConnectivityTestHandler {

	@Override
	public PhysicalConnectivityStatus testConnection(String arg0) throws Exception {
		// TODO: actually do a test here
		status.setConnectionOK(true);
		return status;
	}

}
