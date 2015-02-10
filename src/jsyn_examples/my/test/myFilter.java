package jsyn_examples.my.test;

import com.jsyn.unitgen.UnitFilter;
import com.softsynth.math.AudioMath;

/**
 */
public class myFilter extends UnitFilter
    {
        double attenuation;
        double db;

        public  myFilter()
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
                outputs[i]=amplitude;
                System.out.println("out: "+ outputs[i]);
            }
        }

    }

