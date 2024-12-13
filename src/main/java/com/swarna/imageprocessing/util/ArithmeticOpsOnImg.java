package com.swarna.imageprocessing.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.swarna.imageprocessing.util.Constant.MAX_LENGTH_OF_PGM_IMG;

public class ArithmeticOpsOnImg {

    public static Img add(Integer integerValue, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa + fb;
        return forEachPixelValue(function, integerValue, b);
    }

    public static Img add(Img a, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa + fb;
        return forEachPixelValue(function, a, b);
    }

    public static Img sub(Integer integerValue, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa - fb;
        return forEachPixelValue(function, integerValue, b);
    }

    public static Img sub(Img a, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa - fb;
        return forEachPixelValue(function, a, b);
    }

    public static Img mul(Integer integerValue, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa * fb;
        return forEachPixelValue(function, integerValue, b);
    }

    public static Img mul(Img a, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa * fb;
        return forEachPixelValue(function, a, b);
    }

    public static Img div(Integer integerValue, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa / fb;
        return forEachPixelValue(function, integerValue, b);
    }

    public static Img div(Img a, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa / fb;
        return forEachPixelValue(function, a, b);
    }

    public static Img sqrt(Integer integerValue, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa ^ fb;
        return forEachPixelValue(function, integerValue, b);
    }

    public static Img sqrt(Img a, Img... b) {
        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa ^ fb;
        return forEachPixelValue(function, a, b);
    }

    public static int max(Img img) {
        return Collections.max(img.getBody());
    }

    public static int max(Img img, int fromPixelIndex, int toPixelIndex) {
        return img.getBody().stream()
                .skip(fromPixelIndex)
                .limit(toPixelIndex)
                .reduce(Integer::max)
                .orElseThrow(() -> new IllegalArgumentException("Invalid index, Please provide valid one"));
//        return Collections.max(img.getBody());
    }

    public static int min(Img img) {
        return Collections.min(img.getBody());
    }

//    public static Img calcArithmeticOps(BiFunction<Integer, Integer, Integer> arithmeticOps, Img a, Img... b) {
//        BiFunction<Integer, Integer, Integer> function = (fa, fb) -> fa ^ fb;
//        return calcArithmeticOps(function, a, b);
//    }

    public static Img forEachPixelValue(BiFunction<Integer, Integer, Integer> arithmeticFunction, Img a, Img... b) {
        var valuesA = a.getBody();
        List<Integer> sum = new ArrayList<>();
        for (Img img : b) {
            var valuesB = img.getBody();
            sum = IntStream.range(0, Math.min(valuesA.size(), valuesB.size()))
                    .mapToObj(i -> {
                        int tempSum = arithmeticFunction.apply(valuesA.get(i), valuesB.get(i));
                        tempSum = tempSum < 0 ? 0 : tempSum;
                        tempSum = tempSum > MAX_LENGTH_OF_PGM_IMG ? MAX_LENGTH_OF_PGM_IMG : tempSum;
                        return tempSum;
                    }).toList();
            System.out.println("Total " + sum.size() + " values processed");
        }
        System.out.println("Total no. of INVALID BITS = " + sum.stream().filter(s -> s > 255).count());
        var img = new Img();
        img.setHeaders(a.getHeaders());
        img.setBody(sum);
        return img;
    }

    private static Img forEachPixelValue(BiFunction<Integer, Integer, Integer> arithmeticFunction, Integer integerValue, Img... b) {
        List<String> headerLines = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        boolean header = false;
        for (Img bi : b) {
            var valuesB = bi.getBody();
            sum = IntStream.range(0, valuesB.size())
                    .mapToObj(i -> {
                        int tempSum = arithmeticFunction.apply(integerValue, valuesB.get(i));
                        tempSum = tempSum < 0 ? 0 : tempSum;
                        tempSum = tempSum > MAX_LENGTH_OF_PGM_IMG ? MAX_LENGTH_OF_PGM_IMG : tempSum;
                        return tempSum;
                    }).toList();
            System.out.println("Total " + sum.size() + " values processed");
            if (!header) {
                headerLines = bi.getHeaders();
                header = true;
            }
        }
        System.out.println("Total no. of INVALID BITS = " + sum.stream().filter(s -> s > 255).count());
        var img = new Img();
        img.setHeaders(headerLines);
        img.setBody(sum);
        return img;
    }

    public static Img forEachPixelValue(Function<Integer, Integer> functionForEachCell, Img... b) {
        List<String> headerLines = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        boolean header = false;
        for (Img bi : b) {
            var valuesB = bi.getBody();
            result = IntStream.range(0, valuesB.size())
                    .mapToObj(i -> functionForEachCell.apply(i)).collect(Collectors.toList());
            System.out.println("Total " + result.size() + " values processed");
            result = bi.getBody().stream().map(i -> functionForEachCell.apply(i)).collect(Collectors.toList());
            if (!header) {
                headerLines = bi.getHeaders();
                header = true;
            }
        }
        System.out.println("Total no. of INVALID BITS = " + result.stream().filter(s -> s > 255).count());
        var img = new Img();
        img.setHeaders(headerLines);
        img.setBody(result);
        return img;
    }

}
