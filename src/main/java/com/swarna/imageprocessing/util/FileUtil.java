package com.swarna.imageprocessing.util;

import com.swarna.imageprocessing.util.viewer.img.Img;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static List<String> readImageFile(String filePath) throws IOException {
        var lines = Files.readAllLines(Paths.get(filePath));
        return lines;
    }

    private String writeImageFile(String filePath, List<String> lines) throws IOException, InterruptedException {
        final String OUT_FILE_PATH = filePath + "_out.pgm";
        FileWriter fileWriter = new FileWriter(OUT_FILE_PATH);
        for (String line : lines) {
            fileWriter.append(line).append(System.lineSeparator());
        }
        fileWriter.write("");
        fileWriter.close();
        return OUT_FILE_PATH;
    }

    public static String imwrite(String filePath, Img img) {
        List<String> lines = img.getActualData();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            for (String line : lines) {
                try {
                    fileWriter.append(line).append(System.lineSeparator());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    public static Img imread(String filePath) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var img =  new Img();
        img.setActualData(lines);
        return img;
    }

}
