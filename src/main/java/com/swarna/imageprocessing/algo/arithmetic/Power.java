package com.swarna.imgprocessing.algo.arithmetic;

import static com.swarna.imgprocessing.util.ArithmeticOpsOnImg.forEachPixelValue;
import static com.swarna.imgprocessing.util.FileUtil.imread;
import static com.swarna.imgprocessing.util.ImShow.imshow;
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