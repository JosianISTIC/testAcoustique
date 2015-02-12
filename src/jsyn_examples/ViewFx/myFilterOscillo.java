package ViewFx;

import com.jsyn.unitgen.UnitFilter;
import javafx.scene.chart.XYChart;

/**
 */
public class myFilterOscillo extends UnitFilter
    {
        int i=0;
        XYChart.Series<Number, Number> series;

        public myFilterOscillo(XYChart.Series<Number, Number> s)
        {
            super();
            this.series = s;
        }

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
                series.getData().add(new XYChart.Data<Number, Number>(i+1, amplitude));
                System.out.println("out: "+ outputs[i]);
            }
        }

    }

