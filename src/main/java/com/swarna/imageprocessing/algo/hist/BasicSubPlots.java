package com.swarna.imageprocessing.algo.hist;

import java.util.ArrayList;
import java.util.List;

import static com.swarna.imageprocessing.util.viewer.plot.PlotWindow.*;

public class BasicSubPlots {

    public static void main(String[] args) {
        List<Double> x = List.of(0.35, 1.43, 1.6);
        List<Double> y1 = List.of(0.3, 1.3, 1.55);
        List<Double> y2 = List.of(0.3, 1.3, 1.55);

        subplot(2, 1);
        plot(x, y1);
        plot(x, y2);
    }
}
