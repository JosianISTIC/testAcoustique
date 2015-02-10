package my.test.vca;

import java.util.*;


public class AbstractObservableModel
        extends Observable implements ObservableModel {

    private final Map<String, Set<Observer>> obs;

    protected AbstractObservableModel() {
        obs = new HashMap<>();
    }

    @Override
    public Set<Observer> getObservers(String property) {
        if (property == null) {
            throw new IllegalArgumentException();
        }
        Set<Observer> result = obs.get(property);
        if (result == null) {
            result = Collections.emptySet();
        }
        return result;
    }

    @Override
    public void addObserver(String property, Observer o) {
        if (property == null ||  o == null) {
            throw new IllegalArgumentException();
        }
        if (getObservers(property).contains(o)) {
            throw new IllegalArgumentException();
        }
        Set<Observer> set = obs.get(property);
        if (set == null) {
            set = new HashSet<>();
            obs.put(property, set);
        }
        set.add(o);
        o.update(this, null);
    }

    @Override
    public void removeObserver(String property, Observer o) {
        if (property == null ||  o == null) {
            throw new IllegalArgumentException();
        }
        if (!getObservers(property).contains(o)) {
            throw new IllegalArgumentException();
        }
        Set<Observer> set = obs.get(property);
        set.remove(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
        for (Set<Observer> set : obs.values()) {
            for (Observer o : set) {
                o.update(this, null);
            }
        }
    }

    protected void firePropertyChange(String property, Object value) {
        if (property == null) {
            throw new IllegalArgumentException();
        }

        Set<Observer> set = obs.get(property);
        if (set == null) {
            set = new HashSet<>();
            obs.put(property, set);
        }
        for (Observer o : set) {
            o.update(this, value);
        }
    }

    protected void firePropertyChange(String property,
        Object newValue, Object oldValue) {
        if (property == null) {
            throw new IllegalArgumentException();
        }
        if ((newValue != null && !newValue.equals(oldValue))
            || (newValue == null && oldValue != null)) {
            firePropertyChange(property, newValue);
        }
    }
}
