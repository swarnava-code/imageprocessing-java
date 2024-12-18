package com.swarna.imageprocessing.algo.filter;

import java.util.Collections;
import java.util.List;

import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.viewer.img.ImShow.imshow;

public class Average {

    public static void main(String[] args) {
        var I = imread("src/main/resources/input/generated_noisy_lena.pgm");
        var INPUT = I.copy();
        var J = I.copy();
        int range = 10;
        final int SALT = 255;
        final int PEPPER = 0;
        int N = I.getBody().size();
//        I.forEachIndexAndPixelValue((i, e) -> {
//            if (I.getBody().get(i) == SALT || I.getBody().get(i) == PEPPER) {
//                double sum = 0;
//                int count = 0;
//                int halfRange = range / 2;
//                for (int j = i - halfRange; j < i + halfRange; j++) {
//                    if (j >= 0 && j <= N - 1 && I.getBody().get(j) != SALT && I.getBody().get(j) != PEPPER) {
//                        sum += I.getBody().get(j);
//                        count++;
//                    }
//                }
//                int avg = (int) (sum / count);
//                I.getBody().set(i, avg);
//            }
//        });

        J.forEachIndexAndPixelValue((i, e) -> {
            if (I.getBody().get(i) == SALT || I.getBody().get(i) == PEPPER) {
                int halfRange = range / 2;
                I.range(i - halfRange, i + halfRange);
                List<Integer> subList = I.range(i - halfRange, i + halfRange, ee -> ee != SALT && ee != PEPPER);
                int sum = subList.stream().mapToInt(Integer::intValue).sum();
                int count = subList.size();
                int avg = (int) (sum / count);
                I.getBody().set(i, avg);
            }
        });


        imshow(INPUT.as("INPUT | NOISY IMAGE"),
                I.as("OUTPUT | FILTERED IMAGE"));

    }
}
