package my.test;

import com.jsyn.unitgen.UnitFilter;
import com.softsynth.math.AudioMath;

/**
 */
public class FilterAttenuation  extends UnitFilter
{
    double attenuation;
    double db;

    public  FilterAttenuation(double atennuation)
    {
        super();

    }
    /** This is where the synthesis occurs.
     * It is called in a high priority background thread so do not do
     * anything crazy here like reading a file or doing network I/O.
     * The start and limit allow us to do either block or single sample processing.
     */
    @Override
    public void generate( int start, int limit )
    {
        // Get signal arrays from ports.
        double[] inputs = input.getValues();
        double[] outputs = output.getValues();

        for( int i = start; i < limit; i++ )
        {
           double amplitude = inputs[i]; //amplitude
           outputs[i]=amplitude+ amplitude*attenuation;
        }
    }


    public void setAttenuation(double db)
    {
        this.attenuation =  AudioMath.decibelsToAmplitude(db);
        System.out.println("attenuation ="+attenuation);
    }
}
