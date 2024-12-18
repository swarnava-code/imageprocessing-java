package com.swarna.imageprocessing.algo.logical;

import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.viewer.img.ImShow.imshow;
import static com.swarna.imageprocessing.util.OperationsOnImage.*;

public class LogicalOps {

    public static void main(String[] args) {
        // input
        var I = imread("src/main/resources/input/original_baloon.pgm");
        var J = imread("src/main/resources/input/original_lena.pgm");

        // image processing
        var OR = bitor(I, J);
        var XOR = bitxor(I, J);
        var CMP_I = bitcmp(I);
        var CMP_J = bitcmp(J);
        var AND = bitand(I, J);

        // output
        imshow(
                I.setLabel("Input I"),
                J.setLabel("Input J"),
                OR.setLabel("Result of I OR J"),
                XOR.setLabel("Result of I XOR J"),
                CMP_I.setLabel("Result of Complement of I"),
                CMP_J.setLabel("Result of Complement of J"),
                AND.setLabel("Result of I AND J")
        );
    }

}