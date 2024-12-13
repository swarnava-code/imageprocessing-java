package com.swarna.imageprocessing.algo.arithmetic;

import static com.swarna.imgprocessing.util.FileUtil.imread;
import static com.swarna.imgprocessing.util.ImShow.imshow;
import static java.lang.Math.log;

public class Log {
    public static void main(String[] args) {
        var I = imread("/Users/swarnavachakraborty/Downloads/original_lena.pgm");
        var c1 = 40;
        var c2 = 30;
        var logImageC1 = I.forEachPixelValue(e -> (int) (c1 * (log(1 + e))));
        var logImageC2 = I.forEachPixelValue(e -> (int) (c2 * (log(1 + e))));
        imshow(I, logImageC1, logImageC2);
    }
}
