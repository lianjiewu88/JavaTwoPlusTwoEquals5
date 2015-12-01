package wangPengCommunication;


public interface CommunicationForResultAndErrorReason {
	abstract void startRequest();
	abstract boolean getResult();
	abstract int getErrorReason();
}
