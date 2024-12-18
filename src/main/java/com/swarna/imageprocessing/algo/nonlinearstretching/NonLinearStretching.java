package com.swarna.imageprocessing.algo.nonlinearstretching;

import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.viewer.img.ImShow.imshow;
import static com.swarna.imageprocessing.util.OperationsOnImage.*;
import static java.lang.Math.log;

public class NonLinearStretching {

    public static void main(String[] args) {
        // input
        var I = imread("src/main/resources/input/low_cameraman.pgm");

        // img processing
        var a = min(I);
        var b = max(I);
        var O = forEachPixelValue( e -> (int) (log(e - a + 1) * (255 / log(b - a + 1)))  , I );

        // output
        imshow(I, O);
    }
}
