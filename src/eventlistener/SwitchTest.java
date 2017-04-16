package eventlistener;

public class SwitchTest {

	public static void main(String[] args) {
		SwitchManager manager = new SwitchManager();
        manager.addDoorListener(new TVSwitchListener());

        manager.fireSwitchOn();
        manager.fireSwitchOff();

	}

}
