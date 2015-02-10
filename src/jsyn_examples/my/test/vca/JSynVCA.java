package jsyn_examples.my.test.vca;

import com.jsyn.unitgen.Multiply;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;


public class JSynVCA extends AbstractModule implements VCA {

    /**
     * Represent the output by using a multiplying unit
     * OUT = IN * 10 ^ -(k + AM)
     *     = IN * 10 ^ (-k) * 10 ^ (-AM)
     */
    private Multiply multiply;
    private VCAInFilter in;// INPUT
    private VCAAmFilter am;// INPUT FOR AM (EG)
    private double amplification; //a0


    public JSynVCA() throws IllegalAccessException, InstantiationException {
        makeCircuit();
        amplification = 0;
        Observer obs = ((o, arg) -> in.setAmplitudeCoef(getAmplification()));
        addObserver("amplification", obs);
        //addObserver("fineTuning", obs);
    }

    private void makeCircuit() throws IllegalAccessException,
            InstantiationException {
        multiply = new Multiply();
        in = new VCAInFilter(getAmplification());
        am = new VCAAmFilter();

        addInput("In", in.input);
        addInput("Am", am.input);
        multiply.inputA.connect(in.output);
        multiply.inputB.connect(am.output);
        addOutput("out", multiply.output);

        addEntity(multiply);
        addEntity(in);
        addEntity(am);
    }

    public VCAInFilter getIn() {
        return in;
    }

    public VCAAmFilter getAm() {
        return am;
    }

    public double getAmplification() {
        return amplification;
    }

    public void setAmplification(double amplification) {
        if (amplification < MIN_AMPLIFICATION || amplification > MAX_AMPLIFICATION) {
            throw new IllegalArgumentException();
        }
        double old = this.amplification;
        this.amplification = amplification;
        firePropertyChange("amplification", getAmplification(), old);

    }

    public void setAm(VCAAmFilter am) {
        this.am = am;
        addInput("VCAAm", this.am.input);
        addEntity(this.am);
    }

    public void setIn(VCAInFilter in) {
        this.in = in;
        addInput("VCAIn", this.in.input);
        addEntity(this.in);
    }

}
