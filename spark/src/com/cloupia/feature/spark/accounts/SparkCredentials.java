package com.cloupia.feature.spark.accounts;

import com.cloupia.feature.spark.constants.SparkConstants;
import java.io.IOException;

import javax.jdo.annotations.Persistent;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import com.cisco.cuic.api.client.JSON;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class SparkCredentials {
	
	private String saprkURL;
	private String sparkApiVer;
	private String token;
	private String proxyUrl;
	private String proxyPort;
	
	static Logger logger = Logger.getLogger(SparkCredentials.class);

	public SparkCredentials(String array_address, String username, String password, boolean https, int http_port) {
		
	}

	public SparkCredentials(String array_address, String username, String password, boolean https) {
		
	}

	public SparkCredentials(String contextId) throws Exception {
		logger.info("contextId: " + contextId);
		String accountName = null;
		if (contextId != null) {
			// As the contextId returns as: "account Name;POD Name"
			accountName = contextId.split(";")[0];
		}
		if (accountName == null) {
			throw new Exception("Unable to find the account name");
		}

		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		if (acc == null) {
			throw new Exception("Unable to find the account:" + accountName);
		}
		initFromAccount(acc);
	}

	public SparkCredentials(ReportContext context) throws Exception {
		String contextId = context.getId();
		String accountName = null;
		if (contextId != null) {
			// As the contextId returns as: "account Name;POD Name"
			accountName = contextId.split(";")[0];
		}
		if (accountName == null) {
			throw new Exception("Account not found");
		}
		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		if (acc == null) {
			throw new Exception("Unable to find the account:" + accountName);
		}
		initFromAccount(acc);
	}

	public SparkCredentials(PhysicalInfraAccount acc) throws Exception {
		initFromAccount(acc);
	}

	private void initFromAccount(PhysicalInfraAccount acc) throws Exception {
		
	}
	
}

	
