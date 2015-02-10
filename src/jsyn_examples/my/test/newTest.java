package jsyn_examples.my.test;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.scope.AudioScope;
import com.jsyn.unitgen.*;
import jsyn_examples.my.test.vca.JSynVCA;
import jsyn_examples.my.test.vca.VCA;
import jsyn_examples.my.test.vca.VCAAmFilter;
import jsyn_examples.my.test.vca.VCAInFilter;

import javax.swing.*;

/**
 */
public class newTest {

    static private String g;

    public static void main(String[] args) {

        Synthesizer synth;
        UnitOscillator osc;
        LineOut lineOut= new LineOut();
        synth = JSyn.createSynthesizer();
        myFilter filter = new myFilter();
        EnvelopeDAHDSR envelopeDAHDSR = new EnvelopeDAHDSR();
        osc = new SquareOscillator();
        /**Circuit c = new Circuit();
        Circuit c2 = new Circuit();
        Circuit c3 = new Circuit();

        c.add(osc);
        c2.add(lineOut);
        c3.add(envelopeDAHDSR);

        synth.add(c);
        synth.add(c2);
        synth.add(c3); **/

        synth.add(osc);
        synth.add(filter);
        synth.add(lineOut);
        synth.add(envelopeDAHDSR);

        osc.frequency.set(1);
        envelopeDAHDSR.delay.set(0); //Time in seconds for first stage of the envelope, before the attack.
        envelopeDAHDSR.hold.set(0); //Time in seconds for the plateau between the attack and decay stages.


        envelopeDAHDSR.attack.set(0.1); //Time in seconds for the rising stage of the envelope to go from 0.0 to 1.0.
        envelopeDAHDSR.decay.set(0.1); //Time in seconds for the falling stage to go from 0 dB to -90 dB.
        envelopeDAHDSR.sustain.set(0.0); //Level for the sustain stage.
        envelopeDAHDSR.release.set(0.1); //Time in seconds to go from 0 dB to -90 dB.

        osc.getOutput().connect(envelopeDAHDSR.input);

        /**osc.getOutput().connect(lineOut.input.getConnectablePart(0));
        osc.getOutput().connect(lineOut.input.getConnectablePart(1));**/

        //envelopeDAHDSR.output.connect(lineOut.input);
        envelopeDAHDSR.output.connect(filter.input);
        //filter.output.connect(lineOut.input.getConnectablePart(0));
        //filter.output.connect(lineOut.input.getConnectablePart(1));

        SquareOscillator osc2 = new SquareOscillator();
        osc2.frequency.set(440);
        synth.add(osc2);
        try {

            Multiply multiply = new Multiply();
            synth.add(multiply);
            VCAInFilter in = new VCAInFilter(0);
            osc2.output.connect(in.input);
            VCAAmFilter am = new VCAAmFilter();
            am.input.connect(filter.output);

            multiply.inputA.connect(in.output);
            multiply.inputB.connect(am.output);


            //sortie
            multiply.output.connect(lineOut.input.getConnectablePart(0));
            multiply.output.connect(lineOut.input.getConnectablePart(1));


            //flux entree
            /**
            osc2.output.connect(lineOut.input.getConnectablePart(0));
            osc2.output.connect(lineOut.input.getConnectablePart(1));
            **/

            synth.start();
            lineOut.start();

            AudioScope scope = new AudioScope(synth);
            scope.addProbe( multiply.output );
            scope.addProbe( filter.output );
            scope.addProbe( osc2.output );

            scope.setTriggerMode(AudioScope.TriggerMode.AUTO);

            scope.getModel().getTriggerModel().getLevelModel()
                    .setDoubleValue(0.0001);
            scope.getView().setShowControls(true);

            scope.setTriggerMode( AudioScope.TriggerMode.NORMAL );
            scope.start();


            // Fenetre
            JFrame frame = new JFrame();
            frame.add(scope.getView());
            frame.pack();
            frame.setVisible(true);

        } catch (Exception e){}



        /**c.start();
        c2.start();
        c3.start();µµ
        // Start synthesizer using default stereo output at 44100 Hz.**/


    }


}
