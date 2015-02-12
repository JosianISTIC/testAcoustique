package my.test;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.scope.AudioScope;
import com.jsyn.unitgen.*;
import my.test.vca.VCAAmFilter;
import my.test.vca.VCAInFilter;

import javax.swing.*;

/**
 */
public class TestEG {

    static private String g;

    public static void main(String[] args) {

        Synthesizer synth;

        UnitOscillator osc;
        LineOut lineOut= new LineOut();
        synth = JSyn.createSynthesizer();

        synth.start();
        myFilter filter = new myFilter();
        osc = new SawtoothOscillator();
        synth.add(osc);
        synth.add(filter);
        synth.add(lineOut);
        osc.frequency.set(440);
        osc.amplitude.set(0.6);
        filter.input.connect(osc.output);


        filter.output.connect(lineOut.input.getConnectablePart(0));
        osc.output.connect(lineOut.input.getConnectablePart(1));


        AudioScope scope = new AudioScope(synth);
        scope.addProbe( osc.output );

        scope.setTriggerMode(AudioScope.TriggerMode.AUTO);

        scope.getModel().getTriggerModel().getLevelModel()
                .setDoubleValue(0.0001);
        scope.getView().setShowControls(true);

        scope.setTriggerMode( AudioScope.TriggerMode.NORMAL );

        synth.start();
        lineOut.start();


        // Fenetre
        JFrame frame = new JFrame();
        frame.add(scope.getView());
        frame.pack();
        frame.setVisible(true);



    }


}
