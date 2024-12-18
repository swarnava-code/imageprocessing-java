package com.swarna.imageprocessing.util.viewer.plot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlotWindow {

    private static GridLayout gridLayout;
    private static List<PlotPanel> plotPanels = new ArrayList<>();

    public static void subplot(int row, int col) {
        gridLayout = new GridLayout(row, col);

    }

    public  static void  plot(List<Double> x, List<Double> y) {
        var plot = new PlotPanel(x, y, "");
        plotPanels.add(plot);
        SwingUtilities.invokeLater(() -> {
            Plot frame = new Plot();
            frame.setVisible(true);
        });
    }

    public  static void  plot(List<Double> x, List<Double> y, String title) {
        var plot = new PlotPanel(x, y, title);
        plotPanels.add(plot);

    }

    public static List<PlotPanel> getPlotPanels() {
        return plotPanels;
    }

    public static GridLayout getGridLayout() {
        return gridLayout;
    }

//    public static void openGraphWindow(PlotWindow plotWindow) {
//        SwingUtilities.invokeLater(() -> {
//            Plot frame = new Plot(plotWindow);
//            frame.setVisible(true);
//        });
//    }

    public static void openGraphWindow() {
        SwingUtilities.invokeLater(() -> {
            Plot frame = new Plot();
            frame.setVisible(true);
        });
    }

//    public static void openGraphWindow(PlotWindow plotWindow, String windowTitle) {
//        SwingUtilities.invokeLater(() -> {
//            Plot frame = new Plot(plotWindow, windowTitle);
//            frame.setVisible(true);
//        });
//    }


//    private GridLayout gridLayout;
//    private List<PlotPanel> plotPanels = new ArrayList<>();
//
//    public PlotWindow subplot(int row, int col) {
//        gridLayout = new GridLayout(row, col);
//        return this;
//    }
//
//    public PlotWindow plot(List<Double> x, List<Double> y) {
//        var plot = new PlotPanel(x, y, "");
//        plotPanels.add(plot);
//        return this;
//    }
//
//    public PlotWindow plot(List<Double> x, List<Double> y, String title) {
//        var plot = new PlotPanel(x, y, title);
//        plotPanels.add(plot);
//        return this;
//    }
//
//    public List<PlotPanel> getPlotPanels() {
//        return plotPanels;
//    }
//
//    public GridLayout getGridLayout() {
//        return gridLayout;
//    }
//
//    public static void openGraphWindow(PlotWindow plotWindow) {
//        SwingUtilities.invokeLater(() -> {
//            Plot frame = new Plot(plotWindow);
//            frame.setVisible(true);
//        });
//    }
//
//    public static void openGraphWindow(PlotWindow plotWindow, String windowTitle) {
//        SwingUtilities.invokeLater(() -> {
//            Plot frame = new Plot(plotWindow, windowTitle);
//            frame.setVisible(true);
//        });
//    }
}
