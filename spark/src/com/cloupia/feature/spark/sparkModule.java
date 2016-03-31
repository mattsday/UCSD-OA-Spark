package com.cloupia.feature.spark;

import org.apache.log4j.Logger;

import com.cloupia.feature.spark.accounts.SparkAccount;
import com.cloupia.feature.spark.accounts.SparkConvergedStackBuilder;
import com.cloupia.feature.spark.accounts.SparkInventoryItemHandler;
import com.cloupia.feature.spark.accounts.SparkInventoryListener;
import com.cloupia.feature.spark.accounts.SparkTestConnectionHandler;
import com.cloupia.feature.spark.constants.SparkConstants;
import com.cloupia.feature.spark.tasks.SparkDummyTask;
import com.cloupia.feature.spark.tasks.SparkMessageCreateTask;
import com.cloupia.lib.connector.ConfigItemDef;
import com.cloupia.lib.connector.account.AccountTypeEntry;
import com.cloupia.lib.connector.account.PhysicalAccountTypeManager;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.model.cIM.ReportContextRegistry;
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
		AbstractTask task1   = new SparkDummyTask();
		AbstractTask task2   = new SparkMessageCreateTask();

		AbstractTask[] tasks = new AbstractTask[2];
		tasks[0]  = task1;
		tasks[1]  = task2;

		return tasks;
	}

	@Override
	public void onStart(CustomFeatureRegistry arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Creating New Account Type
	 */
	private void createAccountType(){
		
		AccountTypeEntry entry=new AccountTypeEntry();
		
		// This is mandatory, hold the information for device credential details
		entry.setCredentialClass(SparkAccount.class);
		
		// This is mandatory, type of the Account will be shown in GUI as drill
		// down box
		entry.setAccountType(SparkConstants.INFRA_ACCOUNT_TYPE);
		
		// This is mandatory, label of the Account
		entry.setAccountLabel(SparkConstants.INFRA_ACCOUNT_LABEL);
		
		// This is mandatory, specify the category of the account type ie.,
		// Network / Storage / //Compute
		entry.setCategory(InfraAccountTypes.CAT_STORAGE);
		
		//This is mandatory
		entry.setContextType(ReportContextRegistry.getInstance().getContextByName(SparkConstants.INFRA_ACCOUNT_TYPE).getType());
		
		// This is mandatory, on which accounts either physical or virtual
		// account , new account //type belong to.
		entry.setAccountClass(AccountTypeEntry.PHYSICAL_ACCOUNT);
		
		// Optional , prefix of the task
		entry.setInventoryTaskPrefix("Spark Inventory Task");
		
		//Optional. Group inventory system tasks under this folder. 
		//By default it is grouped under General Tasks
		entry.setWorkflowTaskCategory("Spark Tasks");
		
		// Optional , collect the inventory frequency, whenever required you can
		// change the
		// inventory collection frequency, in mins.
		entry.setInventoryFrequencyInMins(15);
		
		// This is mandatory,under which pod type , the new account type is
		// applicable.
		entry.setPodTypes(new String[] { "spark" } );
		
		
		// This is optional, dependents on the need of session for collecting
		// the inventory
		//entry.setConnectorSessionFactory(new FooSessionFactory());
		
		// This is mandatory, to test the connectivity of the new account. The
		// Handler should be of type PhysicalConnectivityTestHandler.
		entry.setTestConnectionHandler(new SparkTestConnectionHandler());
		
		// This is mandatory, we can implement inventory listener according to
		// the account Type , collect the inventory details.
		entry.setInventoryListener(new SparkInventoryListener());
		
		//This is mandatory , to show in the converged stack view
		entry.setConvergedStackComponentBuilder(new SparkConvergedStackBuilder());
		
		//This is required to show up the details of the stack view in the GUI 
		//entry.setStackViewItemProvider(new FooStackViewProvider());
		
		// This is required credential.If the Credential Policy support is
		// required for this Account type then this is mandatory, can implement
		// credential check against the policyname.
		//entry.setCredentialParser(new FooAccountCredentialParser());
		try {

		// Adding inventory root
			registerInventoryObjects(entry);
			PhysicalAccountTypeManager.getInstance().addNewAccountType(entry);
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

			private void registerInventoryObjects(
					AccountTypeEntry SparkRecoverPointAccountEntry) {
				@SuppressWarnings("unused")
				ConfigItemDef SparkRecoverPointStateInfo = SparkRecoverPointAccountEntry
						.createInventoryRoot("Spark.inventory.root",
								SparkInventoryItemHandler.class);
			}

}
