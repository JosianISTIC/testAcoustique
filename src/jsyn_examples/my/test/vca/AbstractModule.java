package my.test.vca;



import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.UnitGenerator;

import java.util.*;

public abstract class AbstractModule
        extends AbstractObservableModel implements Module {

    private boolean activated;
    private final Circuit circuit;

    private final Map<String, JSynPort> inputs;
    private final Map<String, JSynPort> outputs;


    protected AbstractModule() {
        circuit = new Circuit();
        inputs = new HashMap<>();
        outputs = new HashMap<>();


    }


    @Override
    public Set<String> getInputLabel() {
        return Collections.unmodifiableSet(inputs.keySet());
    }

    @Override
    public Set<String> getOutputLabel() {
        return Collections.unmodifiableSet(outputs.keySet());
    }

    @Override
    public Set<JSynPort> getInput() {
        return Collections.unmodifiableSet(new HashSet<>(inputs.values()));
    }

    @Override
    public Set<JSynPort> getOutput() {
        return Collections.unmodifiableSet(new HashSet<>(outputs.values()));
    }

    @Override
    public JSynPort getInputByLabel(String label) {
        if (label == null || !getInputLabel().contains(label)) {
            throw new IllegalArgumentException();
        }
        return inputs.get(label);
    }

    @Override
    public JSynPort getOutputByLabel(String label) {
        if (label == null || !getOutputLabel().contains(label)) {
            throw new IllegalArgumentException();
        }
        return outputs.get(label);
    }


    @Override
    public boolean isAvailableInput(String label) {
        if (label == null || !getInputLabel().contains(label)) {
            throw new IllegalArgumentException();
        }

        return !inputs.get(label).isConnected();
    }

    @Override
    public boolean isAvailableOutput(String label) {
        if (label == null || !getOutputLabel().contains(label)) {
            throw new IllegalArgumentException();
        }
        return !outputs.get(label).isConnected();
    }

    @Override
    public Circuit getCircuit() {
        return circuit;
    }

    @Override
    public boolean isActivated() {
        return activated;
    }



    @Override
    public void addInput(String label) {
        if (label == null || getInputLabel().contains(label)) {
            throw new IllegalArgumentException();
        }
        addInput(label, new UnitInputPort(label));
    }

    @Override
    public void addOutput(String label) {
        if (label == null || getOutputLabel().contains(label)) {
            throw new IllegalArgumentException();
        }
        addOutput(label, new UnitOutputPort(label));
    }

    protected void addOutput(String label, UnitOutputPort p) {
        if (label == null || getOutputLabel().contains(label) || p == null) {
            throw new IllegalArgumentException();
        }
        JSynPort port = new JSynPort(this, label, p);
        outputs.put(label, port);
    }

    protected void addInput(String label, UnitInputPort p) {
        if (label == null || getInputLabel().contains(label) || p == null) {
            throw new IllegalArgumentException();
        }
        JSynPort port = new JSynPort(this, label, p);
        inputs.put(label, port);
    }

    @Override
    public void setActivated(boolean activated) {
        Boolean old = isActivated();
        this.activated = activated;
        if (activated) {
            circuit.start();
        } else {
            circuit.stop();
        }
        firePropertyChange("activated", isActivated(), old);
    }

    @Override
    public void addEntity(UnitGenerator entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        circuit.add(entity);
    }

}
