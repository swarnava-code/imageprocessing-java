package com.swarna.imageprocessing.algo;


import static com.swarna.imageprocessing.util.ArithmeticOpsOnImg.sub;
import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.FileUtil.imwrite;

public class Negative {

    public static void main(String[] args) {
        var POS_CAT = imread("src/main/resources/input/catty.pgm");
        var NEG_CAT = sub(255, POS_CAT);
        imwrite("src/main/resources/output/catty.pgm", NEG_CAT);
    }
}
