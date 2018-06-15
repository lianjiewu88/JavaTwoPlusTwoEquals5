package jco;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;

/**
 * basic examples for Java to ABAP communication  
 * See help: https://help.sap.com/saphelp_nwpi711/helpdata/en/48/70792c872c1b5ae10000000a42189c/frameset.htm
 */
public class StepByStepClient
{
	static String DESTINATION_NAME = "ABAP_AS_WITHOUT_POOL";
	
    static private Properties prepareProperty(){
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "ldcixcd.wdf.sap.corp");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  "00");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "111");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   "WANGJER");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "Saptest1");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   "en");
        createDestinationDataFile(DESTINATION_NAME, connectProperties);
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3");
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,    "10");
        createDestinationDataFile(DESTINATION_NAME, connectProperties);
        return connectProperties;
    }
    
    static public void main(String[] arg) {
    	createDestinationDataFile(DESTINATION_NAME, prepareProperty());
    	
    	JCoDestination destination = null;
		try {
			destination = JCoDestinationManager.getDestination(DESTINATION_NAME);
			JCoRepository repo = destination.getRepository();
    		JCoFunction stfcConnection = repo.getFunction("ZDIS_GET_UPSELL_MATERIALS");

    		JCoParameterList imports = stfcConnection.getImportParameterList();
        
    		String customerID = "1000040";
    		String materialID = "11";

    		imports.setValue("IV_CUSTOMER_ID", customerID);
    		imports.setValue("IV_MATERIAL_ID", materialID);

    		stfcConnection.execute(destination);
        
    		JCoParameterList exports = stfcConnection.getExportParameterList();
    		
    		// int result = exports.getInt("EV_RESULT");
    	    int abapDuration = exports.getInt("EV_DURATION");
    	    
    	    JCoTable codes = exports.getTable("ET_MATERIALS");
    	    
    	    int row = codes.getNumRows();
    	    System.out.println("Total rows: " + row);
    	    
    	    System.out.println("ABAP duration: " + abapDuration);
    	    
    	    for( int i = 0; i < row; i++){
    	    	codes.setRow(i);
                System.out.println(codes.getString("MATERIAL_ID") + '\t' + codes.getString("MATERIAL_TEXT"));
    	    }
    	    
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    static private void createDestinationDataFile(String destinationName, Properties connectProperties)
    {
        File destCfg = new File(destinationName+".jcoDestination");
        try
        {
            FileOutputStream fos = new FileOutputStream(destCfg, false);
            connectProperties.store(fos, "for tests only !");
            fos.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to create the destination files", e);
        }
    }
    
    
}
