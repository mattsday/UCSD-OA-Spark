package com.cloupia.feature.spark.accounts;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.service.cIM.inframgr.collector.view2.ConnectorCredential;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;


@PersistenceCapable(detachable = "true", table = "spark_account")
public class SparkAccount extends AbstractInfraAccount implements
		ConnectorCredential {

	static Logger logger = Logger.getLogger(SparkAccount.class);

	@Persistent
	private boolean isCredentialPolicy = false;
	
	@Persistent
	@FormField(label = "Spark URL", help = "Spark URL", mandatory = true)
	private String sparkUrl;
	
	@Persistent
	@FormField(label = "Spark API version", help = "Spark API version", mandatory = true)
	private String sparkApiVer;
	
	@Persistent
	@FormField(label = "Token", help = "Spark Token", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_PASSWORD)
	private String token;
	
	@Persistent
	@FormField(label = "Proxy URL", help = "The URL of your proxy server", mandatory = false)
	private String proxyUrl;
	
	@Persistent
	@FormField(label = "Proxy Port", help = "The port of your proxy server", mandatory = false)
	private String proxyPort;
	

	public SparkAccount() {
		
	}
	
	@Override
	public boolean isCredentialPolicy() {
		return false;
	}

	@Override
	public void setCredentialPolicy(boolean isCredentialPolicy) {
		this.isCredentialPolicy = isCredentialPolicy;
	}

	public String getSparkUrl() {
		return sparkUrl;
	}

	public void setSparkUrl(String sparkUrl) {
		this.sparkUrl = sparkUrl;
	}

	public String getSparkApiVer() {
		return sparkApiVer;
	}

	public void setSparkApiVer(String sparkApiVer) {
		this.sparkApiVer = sparkApiVer;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProxyUrl() {
		return proxyUrl;
	}

	public void setProxyUrl(String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPolicy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPolicy(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPort(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUsername(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InfraAccount toInfraAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}
