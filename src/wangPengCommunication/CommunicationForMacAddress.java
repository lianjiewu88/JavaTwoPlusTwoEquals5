package wangPengCommunication;

import java.util.ArrayList;

public interface CommunicationForMacAddress {
	void startRequest();
	ArrayList<String> getMacAddress();
	ArrayList<Integer> getDevId();
}
