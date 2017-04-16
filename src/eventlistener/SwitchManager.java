package eventlistener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class SwitchManager {
    private Collection<ISwitchListener> listeners;

    public void addDoorListener(ISwitchListener listener) {
        if (listeners == null) {
            listeners = new HashSet<ISwitchListener>();
        }
        listeners.add(listener);
    }

    public void removeDoorListener(ISwitchListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    protected void fireSwitchOn() {
        if (listeners == null)
            return;
        SwitchEvent event = new SwitchEvent(this, "open");
        notifyListeners(event);
    }

    protected void fireSwitchOff() {
        if (listeners == null)
            return;
        SwitchEvent event = new SwitchEvent(this, "close");
        notifyListeners(event);
    }

    private void notifyListeners(SwitchEvent event) {
        Iterator<ISwitchListener> iter = listeners.iterator();
        while (iter.hasNext()) {
        	ISwitchListener listener = (ISwitchListener) iter.next();
            listener.switchEvent(event);
        }
    }
}
