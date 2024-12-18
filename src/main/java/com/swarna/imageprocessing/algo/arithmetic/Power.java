package com.swarna.imageprocessing.algo.arithmetic;


import static com.swarna.imageprocessing.util.OperationsOnImage.forEachPixelValue;
import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.viewer.img.ImShow.imshow;
import static java.lang.Math.pow;

public class Power {
    public static void main(String[] args) {
        var I = imread("/Users/swarnavachakraborty/Downloads/original_lena.pgm");
        var v = 1.05;
        var negativeImage = forEachPixelValue(i -> 255 - i, I);
        var powerImage = I.forEachPixelValue(i -> (int) (pow(v, i)));
        var powerImage2 = I.forEachPixelValue(i -> (int) (pow(1.09, i)));
        imshow(I, negativeImage, powerImage, powerImage2);
    }
}