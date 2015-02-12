package ViewFx;

import com.jsyn.JSyn;
import com.jsyn.scope.AudioScope;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.UnitOscillator;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.SwingUtilities;

public class SwingFx extends Application {

    @Override
    public void start (Stage stage) {
        final SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        stage.setTitle("Swing in JavaFX");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            /**@Override
            public void run() {
                swingNode.setContent(new JButton("Click me!"));
            }**/

            @Override
            public void run() {
                com.jsyn.Synthesizer synth = JSyn.createSynthesizer();
                synth.start();
                AudioScope scope = new AudioScope(synth);
                UnitOscillator osc;
                osc = new SquareOscillator();
                synth.add(osc);
                osc.frequency.set(440);
                scope.addProbe( null );

               /** scope.setTriggerMode(AudioScope.TriggerMode.AUTO);

                scope.getModel().getTriggerModel().getLevelModel()
                        .setDoubleValue(0.0001);
                scope.getView().setShowControls(true);**/

                scope.setTriggerMode( AudioScope.TriggerMode.NORMAL );
                scope.start();
                swingNode.setContent(scope.getView());
            }

        });
    }
}