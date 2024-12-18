package com.swarna.imageprocessing.algo.hist;


import com.swarna.imageprocessing.util.viewer.img.Img;

import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.viewer.img.ImShow.imshow;
import static com.swarna.imageprocessing.util.viewer.plot.PlotWindow.*;

/**
 * Here we replace each shades value with some other higher value using Histogram Equalization
 * 1. count no. of shades : int[] countOfPixelValue
 * 2. divide each count with max(colSize, rowSize) : double[] histogram
 * 3. add histogram series in fibonacci style :  double[] fibonacciHistogram
 * 4. Consider the pixel value as index, retrieve value from fibonacciHistogram, and replace pixel in image : Img FIBO_HIST
 */
public class HistogramEqualization {

    public static void main(String[] args) throws CloneNotSupportedException {
        Img I = imread("src/main/resources/input/low_lena.pgm");
        subplot(2, 2);

        subplot(2, 2);

        final int N = 255; // gray color model having total 255 shades

        int[] countOfPixelValue = new int[N];
        for (int i = 0; i < 255; i++) {
            countOfPixelValue[i] = 0;
        }

        I.forEachPixelValue(e -> countOfPixelValue[e] = countOfPixelValue[e] + 1);

        double[] histogram = new double[N];  // histogram = countOfPixelValue / number of rows or cols
        double[] fibonacciHistogram = new double[N];
        int[] size = I.size();
        int maxColOrRow = Math.max(size[0], size[1]);
        for (int i = 0; i < N; i++) {
            histogram[i] = countOfPixelValue[i] / maxColOrRow;
            fibonacciHistogram[i] = histogram[i]; // copying
        }

        // summation in fibonacci style
        for (int i = 2; i < N; i++) {
            fibonacciHistogram[i] = fibonacciHistogram[i - 1] + fibonacciHistogram[i];
        }

        int[] mulFiboHist = new int[N];
        for (int i = 0; i < N; i++) {
            mulFiboHist[i] = (int) fibonacciHistogram[i] * N;
        }

        var MUL_FIBO_HIST = I.copy();
        var FIBO_HIST = I.copy();
        I.forEachIndexAndPixelValue((i, e) -> MUL_FIBO_HIST.setPixelValue(i, mulFiboHist[e]));
        I.forEachIndexAndPixelValue((i, e) -> FIBO_HIST.setPixelValue(i, (int) fibonacciHistogram[e]));
        imshow(I, FIBO_HIST, MUL_FIBO_HIST);

    }
}