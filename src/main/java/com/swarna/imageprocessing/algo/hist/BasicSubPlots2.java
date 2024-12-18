package com.swarna.imageprocessing.algo.hist;

import java.util.ArrayList;
import java.util.List;

import static com.swarna.imageprocessing.util.viewer.plot.PlotWindow.plot;
import static com.swarna.imageprocessing.util.viewer.plot.PlotWindow.subplot;

public class BasicSubPlots2 {

    public static void main(String[] args) {
        List<Double> x = new ArrayList<>();
        List<Double> y1 = new ArrayList<>();
        List<Double> y2 = new ArrayList<>();

        // Generate data points
        for (int i = 0; i < 100; i++) {
            double xi = 2 * Math.PI * i / 99;
            x.add(xi);
            y1.add(Math.sin(xi));
            y2.add(Math.sin(2 * xi));
        }

        subplot(2, 1);
        plot(x, y1);
        plot(x, y2);
    }
}
