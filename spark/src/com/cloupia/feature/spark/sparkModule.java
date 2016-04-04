package com.cloupia.feature.spark;

import org.apache.log4j.Logger;

import com.cloupia.feature.spark.accounts.SparkAccount;
import com.cloupia.feature.spark.accounts.SparkConvergedStackBuilder;
import com.cloupia.feature.spark.accounts.SparkInventoryItemHandler;
import com.cloupia.feature.spark.accounts.SparkInventoryListener;
import com.cloupia.feature.spark.accounts.SparkTestConnectionHandler;
import com.cloupia.feature.spark.constants.SparkConstants;
import com.cloupia.feature.spark.menu.DummyMenuProvider;
import com.cloupia.feature.spark.tasks.DummyScheduleTask;
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
import com.cloupia.service.cIM.inframgr.thresholdmonitor.MonitoringTrigger;
import com.cloupia.service.cIM.inframgr.thresholdmonitor.MonitoringTriggerUtil;


public class sparkModule extends AbstractCloupiaModule {
	
	private static Logger logger = Logger.getLogger(sparkModule.class);

	/* @Override
	public CollectorFactory[] getCollectors() {
		// TODO Auto-generated method stub
		return null;
	} */

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
	public void onStart(CustomFeatureRegistry cfr) {
	//public void onStart(CustomFeatureRegistry arg0) {
		logger.info("Spark Plugin");
		//this is where you would register stuff like scheduled tasks or resource computers

				//when registering new resource types to limit, you need to provide an id to uniquely identify the resource,
				//a description of how that resource is computed, and an instance of the computer itself
				//this.registerResourceLimiter(SparkConstants.DUMMY_VLAN_RESOURCE_TYPE, SparkConstants.DUMMY_VLAN_RESOURCE_DESC, 
				//		new DummyVLANResourceComputer());

				try {
					//this is where you should register LOV providers for use in SimpleDummyAction
				//	cfr.registerLovProviders(SimpleLovProvider.SIMPLE_LOV_PROVIDER, new SimpleLovProvider());
					// Spark SAN protocol type LOV provider.
				//	cfr.registerLovProviders(SparkSanProtocolLovProvider.Spark_SAN_PROTOCOL_LOV_PROVIDER, new SparkSanProtocolLovProvider());	
								
					//you need to provide a unique id for this tabular provider, along with the implementation class, and the
					//index of the selection and display columns, for most cases, you can blindly enter 0
				//	cfr.registerTabularField(SimpleTabularProvider.SIMPLE_TABULAR_PROVIDER, SimpleTabularProvider.class, "0", "0");
					
					//this is where you should add your schedule tasks
				//	addScheduleTask(new DummyScheduleTask());
					
					//registering new report context for use in my dummy menu, good rule of thumb, always register your contexts
					//as early as possible, this way you won't run into any cases where the context does not exist yet and causes
					//an issue elsewhere in the code!
					//ReportContextRegistry.getInstance().register(SparkConstants.DUMMY_CONTEXT_ONE, SparkConstants.DUMMY_CONTEXT_ONE_LABEL);

					//SparkAccount 
					//ReportContextRegistry.getInstance().register(SparkConstants.INFRA_ACCOUNT_TYPE, SparkConstants.INFRA_ACCOUNT_LABEL);
					
					logger.info("Registering as " + SparkConstants.INFRA_ACCOUNT_TYPE);
					ReportContextRegistry.getInstance().register(SparkConstants.INFRA_ACCOUNT_TYPE,
							SparkConstants.INFRA_ACCOUNT_LABEL);
					
					
					
					//Spark Drill down REport 
					//ReportContextRegistry.getInstance().register(SparkConstants.Spark_ACCOUNT_DRILLDOWN_NAME, SparkConstants.Spark_ACCOUNT_DRILLDOWN_LABEL);
					
					//
					// First test at registering a new drillable report.
					//
					//ReportContextRegistry.getInstance().register(SparkConstants.Spark_MY_FIRST_DROPDOWN, SparkConstants.Spark_MY_FIRST_DROPDOWN_LABEL);			
					
					//register the left hand menu provider for the menu item i'm introducing
				//	DummyMenuProvider menuProvider = new DummyMenuProvider();
					
					//Workflow input Types
				//	WorkflowInputTypeDeclaration.registerWFInputs();
					
					// Spark test for perf policies LOV provider.
					//PerformancePoliciesLOVWorkflowInputType.registerWFInputs();
						
					
					//Workflow input Types for multi select
				//	InputTypeDeclaration.registerWFInputs();
					
					//adding new monitoring trigger, note, these new trigger components utilize the dummy context one i've just registered
					//you have to make sure to register contexts before you execute this code, otherwise it won't work
			    //    MonitoringTrigger monTrigger = new MonitoringTrigger(new MonitorDummyDeviceType(), new MonitorDummyDeviceStatusParam());
			    //    MonitoringTriggerUtil.register(monTrigger);
				//	menuProvider.registerWithProvider();
					
					//support for new Account Type
					logger.info("Adding account...");
					createAccountType();
					
				} catch (Exception e) {
					logger.error("Spark Module error registering components.", e);
				}
		
	}
	
	// This method is deprecated, so return null
	public CollectorFactory[] getCollectors() {
		return null;
	}
	
	/**
	 * Creating New Account Type
	 */
	private void createAccountType(){
		logger.info("Creating AccountTypeEntry");
		AccountTypeEntry entry=new AccountTypeEntry();
		
		
		// This is mandatory, hold the information for device credential details
		logger.info("Setting credenital class to SparkAccount.class");
		entry.setCredentialClass(SparkAccount.class);
		
		
		// This is mandatory, type of the Account will be shown in GUI as drill
		// down box
		logger.info("Setting account type to " + SparkConstants.INFRA_ACCOUNT_TYPE);
		entry.setAccountType(SparkConstants.INFRA_ACCOUNT_TYPE);
		
		// This is mandatory, label of the Account
		logger.info("Setting label to " + SparkConstants.INFRA_ACCOUNT_LABEL);
		entry.setAccountLabel(SparkConstants.INFRA_ACCOUNT_LABEL);
		
		// This is mandatory, specify the category of the account type ie.,
		// Network / Storage / //Compute
		logger.info("Setting category to " + InfraAccountTypes.CAT_STORAGE);
		entry.setCategory(InfraAccountTypes.CAT_STORAGE);
		
		//This is mandatory
		logger.info("Setting context type");
		entry.setContextType(
				ReportContextRegistry.getInstance().getContextByName(SparkConstants.INFRA_ACCOUNT_TYPE).getType());
		
		// This is mandatory, on which accounts either physical or virtual
		// account , new account //type belong to.
		entry.setAccountClass(AccountTypeEntry.PHYSICAL_ACCOUNT);
		
		// Optional , prefix of the task
		//entry.setInventoryTaskPrefix("Spark Inventory Task");
		entry.setInventoryTaskPrefix(SparkConstants.TASK_PREFIX);
		
		//Optional. Group inventory system tasks under this folder. 
		//By default it is grouped under General Tasks
		//entry.setWorkflowTaskCategory("Spark Tasks");
		entry.setWorkflowTaskCategory(SparkConstants.WORKFLOW_CATEGORY);
		
		
		// Optional , collect the inventory frequency, whenever required you can
		// change the
		// inventory collection frequency, in mins.
		entry.setInventoryFrequencyInMins(15);
		
		// This is mandatory,under which pod type , the new account type is
		// applicable.
		//entry.setPodTypes(new String[] { "spark" } );
		entry.setPodTypes(new String[] {
				SparkConstants.POD_TYPE, "GenericPod", "FlexPod"
		});
		
		
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
