package com.swarna.imageprocessing.algo.filter;

import java.util.Random;
import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.FileUtil.imwrite;

public class GenerateNoisyImage {

    public static void main(String[] args) {
        var I = imread("src/main/resources/input/original_lena.pgm");
        Random random = new Random();
        var J = I.copy();
        final int levelK = 40; // less noisy than J
        var K = I.forEachPixelValue(e -> {
            int rand = random.nextInt(levelK);
            if (rand == 0) return 1;
            if (rand == 1) return 254;
            return e;
        });
        final int levelJ = 30; // more noisy than K
        I.forEachIndexAndPixelValue((i, e) -> {
            int rand = random.nextInt(levelJ);
            int a = e;
            if (rand == 0) a = 0;
            if (rand == 1) a = 255;
            J.setPixelValue(i, a);
        });
        imwrite("src/main/resources/output/generated_noisy_lena" + random.nextInt(5, 345) + ".pgm", J);
    }
}
