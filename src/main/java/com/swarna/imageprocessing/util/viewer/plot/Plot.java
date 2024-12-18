package com.swarna.imageprocessing.util.viewer.plot;

import javax.swing.*;

public class Plot extends JFrame {
    //PlotWindow plotWindow;


        public Plot() {

        setLayout(PlotWindow.getGridLayout());
            PlotWindow.getPlotPanels().stream().forEach(plot -> add(plot));
        setTitle("Plot View");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

//    public Plot(PlotWindow plotWindow) {
//        this.plotWindow = plotWindow;
//        setLayout(plotWindow.getGridLayout());
//        plotWindow.getPlotPanels().stream().forEach(plot -> add(plot));
//        setTitle("Plot View");
//        setSize(800, 800);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//    }
//
//    public Plot(PlotWindow plotWindow, String windowTitle) {
//        this.plotWindow = plotWindow;
//        setLayout(plotWindow.getGridLayout());
//        plotWindow.getPlotPanels().stream().forEach(plot -> add(plot));
//        setTitle(windowTitle);
//        setSize(800, 800);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//    }

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
