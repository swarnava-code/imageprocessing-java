package com.swarna.imageprocessing.util.viewer.img;

import javax.swing.*;
import java.util.List;

public class ImShow {
    List<String> filePaths;

    public ImShow(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public ImShow(String... filePath) {
        filePaths = List.of(filePath);
    }

    public ImShow() {}

    public void setFilePath(String... filePath) {
        filePaths = List.of(filePath);
    }

    public void openImgWindow(){
        SwingUtilities.invokeLater(() -> new PGMViewer(filePaths.toArray(new String[0])) );
    }

    public static void imshow(String... filePath){
        SwingUtilities.invokeLater(() -> new PGMViewer(filePath) );
    }

    public static void imshow(Img... listOfImageLines){
        SwingUtilities.invokeLater(() -> new PGMViewer(listOfImageLines) );
    }

}
