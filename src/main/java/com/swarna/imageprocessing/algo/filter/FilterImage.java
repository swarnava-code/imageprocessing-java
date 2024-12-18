package com.swarna.imageprocessing.algo.filter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.viewer.img.ImShow.imshow;

public class FilterImage {

    public static void main(String[] args) {
        // user input
        var I = imread("src/main/resources/input/generated_noisy_lena.pgm");
        int range = 10;

        final int SALT = 255;
        final int PEPPER = 0;

        var AVG = I.copy();
        var MEDIAN = I.copy();
        var MAX = I.copy();
        var MIN = I.copy();

        I.forEachIndexAndPixelValue((i, e) -> {
            if (e == SALT || e == PEPPER) {
                int halfRange = range / 2;

                int avg = (int) I.rangeToInt(i - halfRange, i + halfRange,
                        ee -> ee != SALT && ee != PEPPER).average().getAsDouble();

                var sortedList = I.range(i - halfRange, i + halfRange,
                        ee -> ee != SALT && ee != PEPPER);
                Collections.sort(sortedList);
                int median = sortedList.get(sortedList.size() / 2);

                int max = I.range(i - halfRange, i + halfRange,
                        ee -> ee != SALT && ee != PEPPER,
                        (a, b) -> Math.max(a, b)
                );

                int min = I.range(i - halfRange, i + halfRange,
                        ee -> ee != SALT && ee != PEPPER,
                        Math::min
                );
                MAX.setPixelValue(i, max);
                MIN.setPixelValue(i, min);
                AVG.setPixelValue(i, avg);
                MEDIAN.setPixelValue(i, median);
            }
        });

        imshow(I.as("INPUT: NOISY IMAGE"),
                MAX.as("FILTERED IMAGE : MAX"),
                MEDIAN.as("FILTERED IMAGE : MEDIAN"),
                MIN.as("FILTERED IMAGE : MIN"),
                AVG.as("FILTERED IMAGE : AVG"));
    }
}
