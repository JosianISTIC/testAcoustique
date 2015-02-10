package jsyn_examples.my.test;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.scope.AudioScope;
import com.jsyn.unitgen.*;

import javax.swing.*;
import java.util.TimerTask;

/**
 * Created by josian on 02/02/15.
 */
public class testfile {

    static Synthesizer synth;
    static UnitOscillator osc;
    static LineOut lineOut;

   public static void main(String[] args)
   {
       Circuit cirrcuitGeneral = new Circuit();



       // Create a context for the synthesizer.
       synth = JSyn.createSynthesizer();

       // Start synthesizer using default stereo output at 44100 Hz.
       synth.start();

       // Add a tone generator.
       osc = new SineOscillator();
       SineOscillator osc2 = new SineOscillator();
       synth.add( osc );
       synth.add( osc2 );

       // Set the frequency and amplitude for the sine wave.
       osc.frequency.set( 400 );
       osc.amplitude.set( 0.4 );

       osc2.frequency.set( 200 );
       osc2.amplitude.set( 0.4 );

       FilterLowPass fl = new FilterLowPass();
       synth.add(fl);
       osc.output.connect(fl.input);




       synth.add(cirrcuitGeneral);



       UnitInputPort cutoff;
       MultiplyAdd myScalar;
       UnitOutputPort output = new UnitOutputPort();


       cirrcuitGeneral.add(osc);
       myScalar = new MultiplyAdd();
       cirrcuitGeneral.add(myScalar);

       cirrcuitGeneral.addPort(output = myScalar.output);
       cirrcuitGeneral.addPort(cutoff = myScalar.inputC, "Cutoff");

       osc.output.connect( myScalar.inputA );
       myScalar.output.connect(cutoff);


       OutModuleCircuit mod = new OutModuleCircuit();
       synth.add(mod.circuitOut);
       synth.add(mod.lineOut);


       // Connect the oscillator to both channels of the output.
       osc.getOutput().connect( mod.attDroit);




       cutoff.setup( 0.0, 100, 150 );
       cutoff.setMaximum(10000);

       mod.setActivate(true);
       mod.setAttenuation(-12);
       mod.play();

       /**try{
           Thread.sleep(500);
            synth.stop();
           Thread.sleep(1000);
           synth.start();
           mod.play();
           synth.stop();
           System.out.println("FINISH .........");

       }
       catch(Exception e){}**/

      //Vue graphique
       AudioScope scope = new AudioScope(synth);

       //scope.addProbe(fl.output);
       //scope.addProbe(osc.output);
       scope.addProbe(output);

      // scope.addProbe(mod.attDroit.output);
       scope.setTriggerMode(AudioScope.TriggerMode.AUTO);

       scope.getModel().getTriggerModel().getLevelModel()
               .setDoubleValue(0.0001);
       scope.getView().setShowControls(true);
       scope.start();

       // Fenetre
       JFrame frame = new JFrame();
       frame.add(scope.getView());
       frame.pack();
       frame.setVisible(true);

   }

}
