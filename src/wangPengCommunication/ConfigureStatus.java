package wangPengCommunication;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.json.JSONObject;




public class ConfigureStatus implements Runnable, CommunicationForStatus{

	private String ConfigureStatus = null;
	private String IPAddress = null;
	
	
	public  ConfigureStatus(String IPAddress) {
		this.IPAddress = IPAddress;
	}

	@Override
	public void run() {

		Socket client = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		
		try {
			// new socket
			client = new Socket(IPAddress, 8909);
			out = new DataOutputStream(client.getOutputStream());
			in = new DataInputStream(client.getInputStream());
			
			//set field type
			byte type = 1;
			out.writeByte(type);
			out.flush();
			byte retType = in.readByte();

			short retLength = in.readShort();
			byte[] jsonData = new byte[retLength];
			in.read(jsonData);
			
			JSONObject jsonObject = new JSONObject(new String(jsonData));
			String status = jsonObject.getString("respond");
			// Log.i("ConfigureStatus", "status: "+status.toString());
			// Log.i("ConfigureStatus", "retType: "+retType);
			if((retType ==  126))
				ConfigureStatus = status;
			in.close();
			out.close();
			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public String getStatus() {
		// TODO Auto-generated method stub
		return ConfigureStatus;
	}
	
	
}
