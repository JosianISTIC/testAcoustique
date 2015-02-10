package jsyn_examples.my.test.vca;

import com.jsyn.unitgen.UnitFilter;

public class VCAInFilter extends UnitFilter {

    private double amplitudeCoef;


    public VCAInFilter(double amp) {
        super();
        this.amplitudeCoef = amp;
    }

    public VCAInFilter(){
        super();
    }

    public void setAmplitudeCoef(double amplitudeCoef) {

        this.amplitudeCoef = amplitudeCoef;
        System.out.println("ampFilter " + getAmplitudeCoef());
    }

    public double getAmplitudeCoef() {
        return this.amplitudeCoef;

    }
    @Override
    public void generate(int start, int limit) {
        if (start < 0) {
            throw new IllegalArgumentException();
        }
        if (limit < 0) {
            throw new IllegalArgumentException();
        }
        // Get signal arrays from ports.
        double[] inputsIn = input.getValues();//Input IN
        double[] outputs = output.getValues();


        for (int i = start; i < limit; i += 1) {
            double inValue = inputsIn[i];
            outputs[i] = inValue * Math.pow(10, -amplitudeCoef);
            //System.out.println("ampOUT "+outputs[i]);

        }
    }
}
