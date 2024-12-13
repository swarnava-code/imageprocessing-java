
package com.swarna.imageprocessing.util;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PGMViewer extends JFrame {

    public PGMViewer(String... imagePaths) {
        initializeUI(imagePaths);
    }

    public PGMViewer(Img... listOfImageLines) {
        initializeUI(listOfImageLines);
    }


    private void initializeUI(Img... listOfImageLines) {
        setTitle("PGM Viewer");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/main/resources/img_viewer_icon.png").getImage());

        int totalNumberOfImages = listOfImageLines.length;
        int half = totalNumberOfImages / 2;
        int sqrt = (int)Math.sqrt(totalNumberOfImages);
        setLayout(new GridLayout(sqrt, sqrt)); // Display images in rows

        int figureNumber = 1;
        for (Img img : listOfImageLines) {
            try {
                Image pgmImage = loadPGMImage(img);
                if (pgmImage != null) {
                    JPanel imagePanel = new JPanel(new BorderLayout());
                    JLabel imageLabel = new JLabel(new ImageIcon(pgmImage));
                    imageLabel.setBackground(Color.GRAY);
                    JLabel caption = new JLabel(img.getLabel() + " - (" + figureNumber + ")", JLabel.CENTER);

                    imagePanel.add(imageLabel, BorderLayout.CENTER);
                    imagePanel.add(caption, BorderLayout.SOUTH);


                    add(imagePanel);
                    figureNumber++;
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid PGM data: ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to load image from: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        setVisible(true);
    }

    private void initializeUI(String[] imagePaths) {
        setTitle("PGM Viewer");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1)); // Display images in rows

        int figureNumber = 1;
        for (String path : imagePaths) {
            try {
                Image pgmImage = loadPGMImage(path);
                if (pgmImage != null) {
                    JPanel imagePanel = new JPanel(new BorderLayout());
                    JLabel imageLabel = new JLabel(new ImageIcon(pgmImage));
                    JLabel caption = new JLabel("Figure-" + figureNumber, JLabel.CENTER);

                    imagePanel.add(imageLabel, BorderLayout.CENTER);
                    imagePanel.add(caption, BorderLayout.SOUTH);

                    add(imagePanel);
                    figureNumber++;
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid PGM file: " + path,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to load image from " + path + ": " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        setVisible(true);
    }

    private Image loadPGMImage(Img img) throws IOException {
        var lines = img.getActualData();
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("Input lines are empty or null!");
        }

        // Validate and parse PGM header
        String magicNumber = lines.get(0).trim();
        if (!magicNumber.equals("P2")) {
            throw new IOException("Unsupported PGM format: " + magicNumber);
        }

        int index = 1;
        // Skip comments
        while (lines.get(index).startsWith("#")) {
            index++;
        }

        // Parse width and height
        String[] dimensions = lines.get(index).trim().split("\\s+");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);
        index++;

        // Parse max gray value
        int maxGray = Integer.parseInt(lines.get(index).trim());
        index++;
        // Parse pixel data (handle missing or extra newlines)
        int[][] pixels = new int[height][width];
        int x = 0, y = 0;
        for (int i = index; i < lines.size(); i++) {
            String[] pixelRow = lines.get(i).trim().split("\\s+");
            for (String pixel : pixelRow) {
                if (!pixel.isEmpty()) {
                    pixels[y][x] = Integer.parseInt(pixel);
                    x++;
                    if (x == width) {
                        x = 0;
                        y++;
                        if (y == height) break;
                    }
                }
            }
            if (y == height) break;
        }

        if (y != height) {
            throw new IOException("Incomplete or misaligned pixel data.");
        }
        // Create BufferedImage
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (y = 0; y < height; y++) {
            for (x = 0; x < width; x++) {
                int gray = pixels[y][x] * 255 / maxGray; // Normalize to 0-255
                int rgb = (gray << 16) | (gray << 8) | gray; // Create grayscale RGB
                image.setRGB(x, y, rgb);
            }
        }

        return image;

    }

    private Image loadPGMImage(String path) throws IOException {
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            String magicNumber = scanner.nextLine();
            if (!magicNumber.equals("P2")) {
                throw new IOException("Unsupported PGM format: " + magicNumber);
            }

            // Skip comments
            String line;
            while ((line = scanner.nextLine()).startsWith("#")) {
                // Do nothing; skip comments
            }

            // Read width and height
            String[] dimensions = line.split(" ");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            // Read max pixel value
            int maxVal = Integer.parseInt(scanner.nextLine());

            // Read pixel data
            int[][] pixelData = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (scanner.hasNextInt()) {
                        pixelData[i][j] = scanner.nextInt();
                    }
                }
            }

            // Convert pixel data to Image
            return createImageFromPGM(pixelData, width, height, maxVal);
        }
    }

    private Image createImageFromPGM(int[][] pixelData, int width, int height, int maxVal) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray = (pixelData[y][x] * 255) / maxVal; // Normalize to 0-255
                int rgb = new Color(gray, gray, gray).getRGB(); // Create grayscale color
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;
    }

    public static void main(String[] args) {
        String outFilePath1 = "path/to/image1.pgm";
        String outFilePath2 = "path/to/image2.pgm";
        String outFilePath3 = "path/to/image3.pgm";
        String outFilePath4 = "path/to/image4.pgm";

        SwingUtilities.invokeLater(() -> new PGMViewer(outFilePath1, outFilePath2, outFilePath3, outFilePath4));
    }
}