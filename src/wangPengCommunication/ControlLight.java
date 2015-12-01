package wangPengCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.json.JSONObject;



public class ControlLight implements Runnable, CommunicationForResultAndErrorReason{

	private int rmId;
	private int devId;
	private String operation;
	private int reason;
	private boolean isSucceeded = false;
	private String IPAddress = null;
	
	public ControlLight(int rmId, int devId, String operation, String IPAddress) {

		this.rmId = rmId;
		this.devId = devId;
		this.operation = operation;
		this.IPAddress = IPAddress;
	}
	
	private String DataToJson(){
		//package data to json
		JSONObject jsonObject = null;
		
		try {
			//
			jsonObject = new JSONObject();
			jsonObject.put("devId", devId);
			jsonObject.put("rmId", rmId);
			jsonObject.put("oper", operation);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket client = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		
		try {
			client = new Socket(IPAddress, 8909);
			out = new DataOutputStream(client.getOutputStream());
			in = new DataInputStream(client.getInputStream());
			
			byte type = (byte)7;
			short length = (short)DataToJson().length();

			out.writeByte(type);
			out.writeShort(length);

			out.write(DataToJson().getBytes());
			out.flush();
			
			byte retType = in.readByte();

			/*byte low8 = in.readByte();
			short high8 = in.readByte();
			high8 = (short)(high8 << 8);
			short retLength = (short)(low8 + high8);*/
			short retLength = in.readShort();
			// Log.i("ControlLight", "retLength: " + retLength);
			byte[] jsonData = new byte[retLength];
			in.read(jsonData);
			
			// Log.i("ControlLight", "jsonData: " + new String(jsonData));
			JSONObject jsonObject = new JSONObject(new String(jsonData));
			String status = jsonObject.getString("status");
			
			if(retType ==  120 && status.equals("su"))
				isSucceeded = true;
			else if(!status.equals("su")){
				isSucceeded = false;
				reason = jsonObject.getInt("rea");
			}									
			
			in.close();
			out.close();
			client.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
	}

	@Override
	public void startRequest() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(this);
		thread.start();
		try {
			thread.join(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean getResult() {
		// TODO Auto-generated method stub
		return isSucceeded;
	}

	@Override
	public int getErrorReason() {
		// TODO Auto-generated method stub
		return reason;
	}

}
