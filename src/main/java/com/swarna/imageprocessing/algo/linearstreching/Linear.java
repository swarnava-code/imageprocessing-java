package com.swarna.imageprocessing.algo.linearstreching;


import com.swarna.imageprocessing.util.Constant;
import static com.swarna.imageprocessing.util.ArithmeticOpsOnImg.max;
import static com.swarna.imageprocessing.util.ArithmeticOpsOnImg.min;
import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.ImShow.imshow;
import static java.lang.Math.round;

public class Linear {

    public static void main(String[] args) {
        var I = imread("src/main/resources/input/low_lena.pgm");
        var imax = max(I);
        var imin = min(I);
        var imax2 = max(I, 100, 5000);

        System.out.println(imax);
        System.out.println(imax2);

        var omax = Constant.MAX_LENGTH_OF_PGM_IMG;
        var omin = Constant.MIN_LENGTH_OF_PGM_IMG;

        var con = round((omax - omin) / (imax - imin));
        var con2 = round((omax - omin) / (imax2 - imin));

        var O1 = ((I.forEachPixelValue(e -> ((e - imin) * con) + omin)));
        var O2 = ((I.forEachPixelValue(e -> ((e - imin) * con2) + omin)));

        imshow(I, O1.setLabel("imax="+imax), O2.setLabel("imax="+imax2));
    }
}
