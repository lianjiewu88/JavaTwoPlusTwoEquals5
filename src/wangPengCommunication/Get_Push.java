package wangPengCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class Get_Push implements CommunicationForNameAndIdAndNumber, Runnable{

	private ArrayList<String> timeList = new ArrayList<String>();
	private ArrayList<String> msgList = new ArrayList<String>();
	private String server_id;
	private int msgNum;
	private JSONObject jsonObject;

	public Get_Push(String server_id, int request){
		this.server_id = server_id;
		jsonObject = new JSONObject();
		try{
			jsonObject.put("rea", request);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket client = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		try {
			client = new Socket(server_id, 8909);
			out = new DataOutputStream(client.getOutputStream());
			in = new DataInputStream(client.getInputStream());
			byte type = (byte)11;
			// Log.i("Get_push", "type: "+type);
			short length = (short)jsonObject.toString().length();
			out.writeByte(type);
			out.writeShort(length);
			out.writeBytes(jsonObject.toString());
			out.flush();
			byte retType = in.readByte();
			// Log.i("Get_Push", "retType: "+retType);
			short retLength = in.readShort();
			byte[] jsonData = new byte[retLength];
			in.read(jsonData);
			JSONObject jsonObject = new JSONObject(new String(jsonData));
			JSONArray jsonArray = jsonObject.getJSONArray("mess");
			msgNum = jsonArray.length();
			// Log.i("Get_Push", "Json data: "+ jsonObject.toString());
			if(retType == (byte)114) {
				for(int i=0; i<msgNum; i++)
				{
					JSONObject tmp = (JSONObject)jsonArray.get(i);
					timeList.add(tmp.getString("time"));
					msgList.add(tmp.getString("message"));
				}
			}
			in.close();
			out.close();
			client.close();
		}catch(Exception e){
			e.printStackTrace();	
		}
	}

	@Override
	public void startRequest() {
		Thread thread = new Thread(this);
		thread.start();
		// Log.i("DevicesInfomation", "devicesInfomation is running");
		try {
			thread.join(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getTimeList(){
		return timeList;
	}
	
	public ArrayList<String> getMsgList(){
		return msgList;
	}
	
	@Override
	public int getNumber() {
		
		return 0;
	}

	@Override
	public ArrayList<String> getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getUse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getType() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
