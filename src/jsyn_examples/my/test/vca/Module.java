package jsyn_examples.my.test.vca;

import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.UnitGenerator;

import java.util.Set;

/**
 * Defines the behavior of a Module.
 * @inv
 *  getCircuit() != null
 *  getEntities() != null
 *  getInputLabel() != null
 *  getOutputLabel() != null
 *  getInputByLabel() != null
 *  getOutputByLabel() != null
 *  getAllConnectedInput() != null
 *  getAllConnectedOutput() != null
 */
public interface Module extends ObservableModel {



    //request

    /**
     * Return all input labels.
     */
    Set<String> getInputLabel();

    /**
     * Return all output labels.
     */
    Set<String> getOutputLabel();

    /**
     * Return all input labels.
     */
    Set<JSynPort> getInput();

    /**
     * Return all output labels.
     */
    Set<JSynPort> getOutput();


    /**
     * Return the port by label.
     * @pre
     *  label != null
     *  getInputLabel().contains(label)
     */
    JSynPort getInputByLabel(String label);

    /**
     * Return the port by label.
     * @pre
     *  label != null
     *  getOutputLabel().contains(label)
     */
    JSynPort getOutputByLabel(String label);

    /**
     * Return true if the input is available.
     * @pre
     *  label != null
     *  getInputLabel().contains(label)
     */
    boolean isAvailableInput(String label);

    /**
     * Return true if the output is available.
     * @pre
     *  label != null
     *  getOutputLabel().contains(label)
     */
    boolean isAvailableOutput(String label);

    /**
     * Return the circuit.
     */
    Circuit getCircuit();

    /**
     * Return the activate status.
     */
    boolean isActivated();


    //command


    /**
     * Add a input port.
     * @pre
     *  label != null
     *  !getInputLabel().contains(label)
     * @post
     *  isAvailableInput(label)
     *  getInputLabel().contains(label)
     */
    void addInput(String label);

    /**
     * Add an output port.
     * @pre
     *  label != null
     *  !getOutputLabel().contains(label)
     * @post
     *  isAvailableOutput(label)
     *  getOutputLabel().contains(label)
     */
    void addOutput(String label);


    /**
     * Set the activate status.
     * @post
     *  isActivated() == activated
     */
    void setActivated(boolean activated);

    /**
     * Add Generator Entity.
     * @pre
     *  entity != null
     */
    void addEntity(UnitGenerator entity);

}
