package wangPengCommunication;


import java.util.ArrayList;


public interface CommunicationForNameAndIdAndNumber {
	void startRequest();
	int getNumber();
	ArrayList<String> getName();
	ArrayList<Integer> getID();
	ArrayList<Integer> getState();
	ArrayList<Integer> getUse();
	ArrayList<String> getType();
}
