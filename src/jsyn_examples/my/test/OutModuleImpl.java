package my.test;

import com.jsyn.Synthesizer;
import com.jsyn.unitgen.*;
import com.softsynth.math.AudioMath;

/**
 *
 */
public class OutModuleImpl {

    double attenuationDb;
    boolean running;
    UnitOscillator lineIn;
    LineOut lineOut;

    UnitSource l;

    public boolean isActivate() {
        return running;
    }


    public double getAttenuation() {
        return attenuationDb;
    }


    public void setActivate(Boolean b) {
        this.running = b;
    }


    public void setAttenuation(double db) {
        this.attenuationDb =db;
    }


    public void setLineIn(UnitSource lineIn) {

        // Add a stereo audio output unit.
        Synthesizer synth= testfile.synth;
        synth.add( lineOut = new LineOut() );


        // Connect the oscillator to both channels of the output.
        lineIn.getOutput().connect( 0, lineOut.input, 0 );
        lineIn.getOutput().connect( 0, lineOut.input, 1 );
    }

    public void play()
    {

        //convert decibel into amplitude
        double ampl = AudioMath.decibelsToAmplitude(attenuationDb);
        double currentAmpl = lineIn.amplitude.get();
        double finalAmpl =currentAmpl-ampl;
        System.out.println( "db = "+attenuationDb+" ampl = "+ampl);
        System.out.println( "currentAmpl = "+currentAmpl);
        System.out.println( "finalAmpl = "+finalAmpl);

        //attenuate the signal
        lineIn.amplitude.set(finalAmpl);

        // We only need to start the LineOut. It will pull data from the
        // oscillator.

        if(isActivate())
        {
            this.lineOut.start();

            System.out.println( "You should now be hearing a sine wave. ---------" );

            // Sleep while the sound is generated in the background.
            Synthesizer synth= testfile.synth;
            try
            {
                double time = testfile.synth.getCurrentTime();
                System.out.println( "time = " + time );
                // Sleep for a few seconds.
                synth.sleepUntil( time + 5.0 );
            } catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            finally {
                synth.stop();
            }
            System.out.println( "Stop playing. -------------------" );
            // Stop everything.
        }
        else
        {

        }

    }
}
