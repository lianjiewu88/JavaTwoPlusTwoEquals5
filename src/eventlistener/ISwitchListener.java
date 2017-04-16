package eventlistener;

import java.util.EventListener;

public interface ISwitchListener extends EventListener {
    public void switchEvent(SwitchEvent event);
}
