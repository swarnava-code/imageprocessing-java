package com.swarna.imageprocessing.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Img {
    private List<String> headers;
    private List<Integer> body;
    private String label = "Figure";

    public Img(List<String> actualData) {
        //this.actualData = actualData;
        this.body = actualData.stream().filter(l -> l.length() > Constant.MAX_LENGTH_OF_HEADER_LINE)
                .flatMap(l -> Arrays.stream(l.split(" "))).map(Integer::parseInt).collect(Collectors.toList());
        this.headers = actualData.stream().filter(l -> l.length() <= Constant.MAX_LENGTH_OF_HEADER_LINE).collect(Collectors.toList());
    }

    public Img() {
    }

    public void setActualData(List<String> actualData) {
        //this.actualData = actualData;
        this.body = actualData.stream().filter(l -> l.length() > Constant.MAX_LENGTH_OF_HEADER_LINE)
                .flatMap(l -> Arrays.stream(l.split(" "))).map(Integer::parseInt).collect(Collectors.toList());
        this.headers = actualData.stream().filter(l -> l.length() <= Constant.MAX_LENGTH_OF_HEADER_LINE).collect(Collectors.toList());
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void setBody(List<Integer> body) {
        this.body = body;
    }

    public List<Integer> getBody() {
        return body;
    }

    public List<String> getActualData() {
        return Stream.concat(
                headers.stream(),
                body.stream().map(i -> i.toString()).collect(Collectors.toList()).stream()).collect(Collectors.toList()
        );
    }

    public List<String> getHeaders() {
        return headers;
    }

    public Img setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Img forEachPixelValue(Function<Integer, Integer> functionForEachCell) {
        List<Integer> result;
        result = this.body.stream().map(i -> functionForEachCell.apply(i))
                .map(e -> {
                    e = e > Constant.MAX_LENGTH_OF_PGM_IMG ? 255 : e;
                    e = e < Constant.MIN_LENGTH_OF_PGM_IMG ? 0 : e;
                    return e;
                })
                .collect(Collectors.toList());
        System.out.println("Total no. of INVALID BITS = " + result.stream().filter(s -> s > 255).count());
        var img = new Img();
        img.setHeaders(this.headers);
        img.setBody(result);
        return img;
    }
}
