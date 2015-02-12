package my.test.vca;

import com.jsyn.unitgen.UnitFilter;

/**
 * Defines the behavior of a AmplitudeModulationFilter.
 */
public class AmplitudeModulationFilter extends UnitFilter {

    private double coeff;

    public AmplitudeModulationFilter(double coeff) {
        this.coeff = coeff;
    }

    /**
     * Return the coeff.
     */
    public double getCoeff() {
        return coeff;
    }


    /**
     * Transform the data which entered in the filter.
     * @pre
     *  start >= 0
     *  limit >= 0
     */
    @Override
    public void generate(int start, int limit) {
        if (start < 0) {
            throw new IllegalArgumentException();
        }
        if (limit < 0) {
            throw new IllegalArgumentException();
        }
        double[] inputs = input.getValues();
        double[] outputs = output.getValues();

        for (int i = start; i < limit; i += 1) {
            double x = inputs[i];
            outputs[i] = x * coeff;
        }
    }

    /**
     * Set the coeff.
     * @post
     *  getCoeff() == coeff
     */
    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

}

