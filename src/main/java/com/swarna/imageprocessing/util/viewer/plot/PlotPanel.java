package com.swarna.imageprocessing.util.viewer.plot;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class PlotPanel extends JPanel {
    private List<Double> x, y;
    private String title;

    public PlotPanel(List<Double> x, List<Double> y, String title) {
        this.x = x;
        this.y = y;
        this.title = title;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int padding = 40;
        int labelPadding = 20;

        // Draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);

        // Create hatch marks and grid lines for y-axis.
        int yDivisions = 10;
        for (int i = 0; i < yDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = width - padding;
            int y0 = height - ((i * (height - padding * 2 - labelPadding)) / yDivisions + padding);
            int y1 = y0;
            if (y.size() > 0) {
                g2.setColor(Color.GRAY);
                g2.drawLine(padding + labelPadding + 1 + labelPadding, y0, x1, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinY() + (getMaxY() - getMinY()) * ((i * 1.0) / yDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, padding + labelPadding - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x0 + 4, y1);
        }

        // and for x-axis
        int xDivisions = 10;
        for (int i = 0; i < xDivisions + 1; i++) {
            int x0 = i * (width - padding * 2 - labelPadding) / xDivisions + padding + labelPadding;
            int x1 = x0;
            int y0 = height - padding - labelPadding;
            int y1 = y0 - 4;
            if (x.size() > 1) {
                g2.setColor(Color.GRAY);
                g2.drawLine(x0, height - padding - labelPadding - 1 - labelPadding, x1, padding);
                g2.setColor(Color.BLACK);
                String xLabel = ((int) ((getMinX() + (getMaxX() - getMinX()) * ((i * 1.0) / xDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(xLabel);
                g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // Draw axes
        //g2.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding);
        //g2.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding);

        // Draw title
        FontMetrics metrics = g2.getFontMetrics();
        int titleWidth = metrics.stringWidth(title);
        g2.drawString(title, (width - titleWidth) / 2, padding - 10);

        // Draw plot
//        g2.setColor(Color.BLUE);
//        for (int i = 1; i < x.size(); i++) {
//            int x1 = (int) (padding + labelPadding + (x.get(i - 1) / getMaxX()) * (width - 2 * padding - labelPadding));
//            int y1 = (int) (height - padding - labelPadding - (y.get(i - 1) / getMaxY()) * (height - 2 * padding - labelPadding));
//            int x2 = (int) (padding + labelPadding + (x.get(i) / getMaxX()) * (width - 2 * padding - labelPadding));
//            int y2 = (int) (height - padding - labelPadding - (y.get(i) / getMaxY()) * (height - 2 * padding - labelPadding));
//            g2.drawLine(x1, y1, x2, y2);
//        }

        g2.setColor(Color.BLUE);
        // Calculate the scale factors
        double xScale = (double) (width - 2 * padding - labelPadding) / (getMaxX() - getMinX());
        double yScale = (double) (height - 2 * padding - labelPadding) / (getMaxY() - getMinY());
        for (int i = 1; i < x.size(); i++) {
            int x1 = padding + labelPadding + (int) ((x.get(i - 1) - getMinX()) * xScale);
            int y1 = height - padding - labelPadding - (int) ((y.get(i - 1) - getMinY()) * yScale);
            int x2 = padding + labelPadding + (int) ((x.get(i) - getMinX()) * xScale);
            int y2 = height - padding - labelPadding - (int) ((y.get(i) - getMinY()) * yScale);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private double getMinX() {
        return 0; // Since linspace starts from 0
    }

    private double getMaxX() {
        return 2 * Math.PI; // As per linspace(0, 2 * PI, 100)
    }

    private double getMinY() {
        return -1; // Min value for sin and sin(2x)
    }

    private double getMaxY() {
        return 1; // Max value for sin and sin(2x)
    }
}