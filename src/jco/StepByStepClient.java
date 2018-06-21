package jco;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Properties;

import sun.misc.BASE64Decoder;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;

/**
 * basic examples for Java to ABAP communication  
 * See help: https://help.sap.com/saphelp_nwpi711/helpdata/en/48/70792c872c1b5ae10000000a42189c/frameset.htm
 */
public class StepByStepClient
{
	static String DESTINATION_NAME = "ABAP_AS_WITHOUT_POOL";
	static public final String ABAP_DURATION = "abapLayerDuration";
	static public final String UPSELL_PRODUCT = "upsellProducts";
	static public final String PRODUCT_ID = "productID";
	static public final String PRODUCT_TEXT = "productText";
	
	static private final String FILE_ID = "fileID";
	static private final String FILE_OWNER = "fileOwner";
	static private final String FILE_CDATE = "fileCreationDate";
	static private final String FILE_NAME = "fileName";
	static private final String FILE_CONTENT = "fileContent";
	static private final String PRODUCT_IMAGES = "ProductImages";
	
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
    
    static private void getProductImageTest(){
    	JCoDestination destination = null;
		try {
			destination = JCoDestinationManager.getDestination(DESTINATION_NAME);
			JCoRepository repo = destination.getRepository();
    		JCoFunction stfcConnection = repo.getFunction("ZDIS_GET_MATERIAL_IMAGES");

    		JCoParameterList imports = stfcConnection.getImportParameterList();
        
    		String materialID = "16";

    		imports.setValue("IV_MATERIAL_ID", materialID);

    		stfcConnection.execute(destination);
        
    		JCoParameterList exports = stfcConnection.getExportParameterList();
    		
    	    int abapDuration = exports.getInt("EV_DURATION");
    	    
    	    StringBuilder sb = new StringBuilder();
    	    sb.append("{ \"" + ABAP_DURATION + "\": " + abapDuration + ",");
    	    
    	    sb.append("\"" + UPSELL_PRODUCT + "\":[");
    	    
    	    JCoTable codes = exports.getTable("ET_IMAGES");
    	    
    	    int row = codes.getNumRows();
    	    System.out.println("Total rows: " + row);
    	    
    	    System.out.println("ABAP duration: " + abapDuration);
    	    
    	    for( int i = 0; i < row; i++){
    	    	codes.setRow(i);
                sb.append("{\"" + FILE_ID + "\":" + codes.getString("FILEID") + ","
                		+ "\"" + FILE_OWNER + "\":\"" + codes.getString("OWNER") + "\"" + ",");
                sb.append("{\"" + FILE_CDATE + "\":" + codes.getString("CREATION_DATE") + ","
                		+ "\"" + FILE_NAME + "\":\"" + codes.getString("FILENAME") + "\""); 
                
                storeLocalFile(codes);
                if( i < row - 1){
                	sb.append("},");
                }
                else{
                	sb.append("}");
                }
    	    }
    	    sb.append("]}");
    	    
    	    System.out.println("Final json: " + sb.toString());
    	    
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    static private void storeLocalFile(JCoTable codes){
    	InputStream is = codes.getBinaryStream("FILECONTENT");
    	try {
    		File file = new File("c:\\temp\\" + codes.getString("FILENAME"));

    		byte[] bytes = new byte[is.available()];
    		is.read(bytes);
    		
    		/*String asB64 = Base64.getEncoder().encodeToString(bytes);
    		
    		byte[] asBytes = Base64.getDecoder().decode(asB64);*/
    		
			OutputStream output = new FileOutputStream(file);

			BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);

			bufferedOutput.write(bytes);
			
			bufferedOutput.close();
			is.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
     }
    
    static private void getUpsellProductTest(){
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
    	    
    	    StringBuilder sb = new StringBuilder();
    	    sb.append("{ \"" + ABAP_DURATION + "\": " + abapDuration + ",");
    	    
    	    sb.append("\"" + UPSELL_PRODUCT + "\":[");
    	    
    	    JCoTable codes = exports.getTable("ET_MATERIALS");
    	    
    	    int row = codes.getNumRows();
    	    System.out.println("Total rows: " + row);
    	    
    	    System.out.println("ABAP duration: " + abapDuration);
    	    
    	    for( int i = 0; i < row; i++){
    	    	codes.setRow(i);
                System.out.println(codes.getString("MATERIAL_ID") + '\t' + codes.getString("MATERIAL_TEXT"));
                sb.append("{\"" + PRODUCT_ID + "\":" + codes.getString("MATERIAL_ID") + ","
                		+ "\"" + PRODUCT_TEXT + "\":\"" + codes.getString("MATERIAL_TEXT") + "\"");
                if( i < row - 1){
                	sb.append("},");
                }
                else{
                	sb.append("}");
                }
    	    }
    	    sb.append("]}");
    	    
    	    System.out.println("Final json: " + sb.toString());
    	    
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    static public void main(String[] arg) {
    	
    	getProductImageTest();
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
