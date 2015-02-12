package my.test.vca;


import com.jsyn.unitgen.UnitFilter;

public class VCAAmFilter extends UnitFilter {

    @Override
    public void generate(int start, int limit) {

        if (start < 0) {
            throw new IllegalArgumentException();
        }
        if (limit < 0) {
            throw new IllegalArgumentException();
        }
        // Get signal arrays from ports.
        double[] inputsAm = input.getValues();//Input IN
        double[] outputs = output.getValues();


        for (int i = start; i < limit; i += 1) {
            double amValue = inputsAm[i];
            //outputs[i] =  Math.pow(10,-(amValue));
            outputs[i] =  amValue;
        }
    }
}
