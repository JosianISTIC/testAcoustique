package my.test;

import com.jsyn.unitgen.UnitOscillator;

/**
 * Created by josian on 09/02/15.
 */
public class myUnitOscillator extends UnitOscillator {


    @Override
    public void generate(int i, int i2) {
        double[] outputs = output.getValues();
        outputs[i]=200;
        //System.out.println(outputs[i]);
    }
}
