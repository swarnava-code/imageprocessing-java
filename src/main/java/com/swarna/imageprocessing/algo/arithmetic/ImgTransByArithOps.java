package com.swarna.imageprocessing.algo.arithmetic;


import static com.swarna.imageprocessing.util.ArithmeticOpsOnImg.*;
import static com.swarna.imageprocessing.util.FileUtil.imread;
import static com.swarna.imageprocessing.util.ImShow.imshow;

public class ImgTransByArithOps {

    public static void main(String[] args) {
        var I = imread("/Users/swarnavachakraborty/Downloads/original_lena.pgm");
        var J = imread("/Users/swarnavachakraborty/Downloads/original_baloon.pgm");
        var SUM = add(I, J);
        var SUB = sub(I, J);
        var MUL = mul(I, J);
        var DIV = div(I, J);
        imshow(
                I.setLabel("Input"),
                J.setLabel("Input"),
                SUM.setLabel("Output Sum"),
                SUB.setLabel("Output SUB"),
                MUL.setLabel("Output MUL"),
                DIV.setLabel("Output DIV")
        );
    }
}