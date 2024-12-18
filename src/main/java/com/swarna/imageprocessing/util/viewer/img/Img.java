package com.swarna.imageprocessing.util.viewer.img;

import com.swarna.imageprocessing.util.Constant;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.swarna.imageprocessing.util.Constant.MAX_LENGTH_OF_PGM_IMG;

//import static java.util.stream.Nodes.collect;

public class Img implements Cloneable {
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
//        var oo = actualData.stream().filter(l -> l.length() > Constant.MAX_LENGTH_OF_HEADER_LINE)
//                .flatMap(l -> Arrays.stream(l.split(" "))).map(Integer::parseInt).collect(Collectors.toList());
//        var hh = actualData.stream().filter(l -> l.length() <= Constant.MAX_LENGTH_OF_HEADER_LINE).collect(Collectors.toList());


        headers = new ArrayList<>();
        body = new ArrayList<>();

        int ii = 0;
        while (!actualData.get(ii).trim().equals(""+MAX_LENGTH_OF_PGM_IMG)) {
            headers.add(actualData.get(ii));
            ii++;
        }

        headers.add(actualData.get(ii));

        for (int i = ii+1; i < actualData.size(); i++) {
//            String line = actualData.get(i);


            Arrays.asList(actualData.get(i).split(" ")).stream().map(Integer::parseInt).forEach(e -> body.add(e));
        }


//        System.out.println();

//        this.body = actualData.stream().filter(l -> l.length() > Constant.MAX_LENGTH_OF_HEADER_LINE)
//                .flatMap(l -> Arrays.stream(l.split(" "))).map(Integer::parseInt).collect(Collectors.toList());
//        this.headers = actualData.stream().filter(l -> l.length() <= Constant.MAX_LENGTH_OF_HEADER_LINE).collect(Collectors.toList());
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

    @Override
    public Img clone() throws CloneNotSupportedException {
        Img clonedVersion = (Img) super.clone();
        if (clonedVersion.body != null) {
            clonedVersion.body = new ArrayList<>(this.body);
        }
        if (clonedVersion.headers != null) {
            clonedVersion.headers = new ArrayList<>(this.headers);
        }
        return clonedVersion;
    }

    public Img copy() {
        try {
            return clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getActualData() {
//        int[] size = size();
//        int row = size[0];
//        int col = size[1];
//        int[][] arr = new int[row][col];
//
//        List<String> newBody = new ArrayList<>();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        int j;
//        for (j = 1; j < body.size(); j++) {
//            if (j % col == 0) {
//                newBody.add(stringBuilder.toString());
//                stringBuilder = new StringBuilder();
//            }
//            stringBuilder.append(body.get(j - 1) + " ");
//
//        }
//        if (j % col == 0) {
//            newBody.add(stringBuilder.toString());
//            stringBuilder = null;
//        }
//
//        int sizeTT = newBody.size();
//        System.out.println(sizeTT);


//        for (int i = 0; i < body.size(); i++) {
//            System.out.print(".");
//            int rowIndex = i / col;
//            int colIndex = i - ((col * rowIndex) + 1);
//            arr[rowIndex][colIndex] = body.get(i);
//        }
//
//        for (int i = 0; i < arr.length; i++) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (int j = 0; j < arr[0].length; j++) {
//                stringBuilder.append(arr[i][j]+" ");
//            }
//            newBody.add(stringBuilder.toString());
//            stringBuilder = new StringBuilder();
//        }
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

    public Img as(String label) {
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
                    e = e > MAX_LENGTH_OF_PGM_IMG ? 255 : e;
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

    public void setPixelValue(int index, int element) {
        var src = getBody();
        src.set(index, element);
        Collections.copy(this.body, src);
    }

    /**
     * <h2>Example</h2>
     * forEachIndexAndPixelValue ( (i, e) -> J.setPixelValue(i, res[e]) );
     *
     * @param indexAndElement
     * @return
     */
    public Img forEachIndexAndPixelValue(BiConsumer<Integer, Integer> indexAndElement) {
        IntStream.range(0, body.size()).forEach(index -> indexAndElement.accept(index, body.get(index)));
        return this;
    }

    public List<Integer> range(int fromIndex, int toIndex) {
        return body.stream().skip(fromIndex).limit(toIndex).collect(Collectors.toList());
    }

    //j >= 0 && j <= N - 1 && I.getBody().get(j) != SALT && I.getBody().get(j) != PEPPER
    public List<Integer> range(int fromIndex, int toIndex, Predicate<Integer> filter) {
        return body.stream().skip(fromIndex).limit(toIndex-fromIndex+1)
                .filter(e -> filter.test(e))
                .collect(Collectors.toList());
    }//subList.stream().mapToInt(Integer::intValue).sum();


    public IntStream rangeToInt(int fromIndex, int toIndex, Predicate<Integer> filter) {
        return body.stream().skip(fromIndex).limit(toIndex-fromIndex+1)
                .filter(e -> filter.test(e))
                .mapToInt(Integer::intValue);
    }

    public int sum(int fromIndex, int toIndex, Predicate<Integer> filter) {
         return range(fromIndex, toIndex, filter).stream().mapToInt(Integer::intValue).sum();
    }

    public int range(int fromIndex, int toIndex, Predicate<Integer> filter, BinaryOperator<Integer> operation) {
        return range(fromIndex, toIndex, filter).stream().reduce(0, operation);
    }


    /**
     * @return [0:row, 1:col]
     */
    public int[] size() {
        int[] size = null;
        for (String line : headers) {
            try {
                String[] arr = line.trim().split(" ");
                if (arr.length == 2) {
                    int row = Integer.parseInt(arr[0]);
                    int col = Integer.parseInt(arr[1]);
                    size = new int[]{row, col};
                    break;
                }
            } catch (Exception e) {

            }
        }
        return size;
    }
}
