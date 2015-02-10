package my.test;

import com.jsyn.Synthesizer;
import com.jsyn.ports.ConnectableInput;
import com.jsyn.ports.ConnectableOutput;
import com.jsyn.ports.PortBlockPart;
import com.jsyn.unitgen.*;
import com.softsynth.math.AudioMath;

/**

 */
public class OutModuleCircuit {
    double attenuationDb;
    boolean running;


    LineOut lineOut;
    FilterAttenuation attDroit;

    PassThrough passThroughLeft;
    PassThrough passThroughRight;

    public Circuit circuitOut;

    public OutModuleCircuit()
    {
        circuitOut = new Circuit();

        double att = 10;
        //Initialisation des filtres pour l'attenuation
        attDroit = new FilterAttenuation(att);
        lineOut = new LineOut();

        circuitOut.add(attDroit);
        circuitOut.add(lineOut);

        passThroughLeft = new PassThrough();
        passThroughRight = new PassThrough();
        circuitOut.add(passThroughLeft);
        circuitOut.add(passThroughRight);


        //attGauche.output.connect(passThroughLeft.input);
        //attDroit.output.connect(passThroughRight.input);


        lineOut = new LineOut();
        attDroit.getOutput().connect(lineOut.input.getConnectablePart(0));

        //passThroughLeft.output.connect(lineOut.input.getConnectablePart(0));
        //passThroughRight.output.connect(lineOut.input.getConnectablePart(1));



    }


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
        attDroit.setAttenuation(db);

        this.attenuationDb = db;
        refresh();
    }


    public void play()
    {
        lineOut.start();
    }

    public void refresh()
    {
        attDroit = new FilterAttenuation(attenuationDb);
    }

}
