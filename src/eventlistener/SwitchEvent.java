package eventlistener;

import java.util.EventObject;

public class SwitchEvent extends EventObject implements cloneable {

    private String switchState = "";

    public SwitchEvent(Object source, String switchState) {
        super(source);
        this.switchState = switchState;
    }

    public void setSwitchState(String switchState) {
        this.switchState = switchState;
    }

    public String getSwitchState() {
        return this.switchState;
    }
}
