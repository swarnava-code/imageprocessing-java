# Java MATLAB Style Utils

A collection of Java utilities designed to mimic MATLAB style programming using predefined classes and packages. This repository aims to simplify certain Java coding tasks by providing MATLAB-like functions and syntax.


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [Contributing](#contributing)
- [Contact](#contact)


## Introduction

Welcome to Java MATLAB Style Utils! This project is designed to help you write simple Java code in a style similar to MATLAB. It includes a variety of functions and utilities that make Java programming more intuitive and concise, just like MATLAB.


## Features

- **MATLAB-like functions**: Provides functions for matrix operations, mathematical computations, and data processing.
- **Easy to use**: Designed to be easy to integrate and use in your Java projects.
- **Predefined classes and packages**: Utilizes standard Java libraries to replicate MATLAB functionalities.
- **Well-documented**: Each function comes with detailed documentation and examples.


## Installation

To use this project, you can clone the repository and include it in your Java project:

```bash
git clone https://github.com/swarnava-code/imageprocessing-java.git
```


## Usage

- Create a Java class.
- Create the `main()` method.
- Import the necessary static methods according to your requirements.
- Use the predefined MATLAB-style object-oriented methods for easy implementation.

Checkout all utility classes here:
[src/main/java/com/swarna/imageprocessing/util/...](src/main/java/com/swarna/imageprocessing/util)

```java


```

## Example

Transforming a positive image into Negative
```java
public class Negative {

    public static void main(String[] args) {
        var POS_CAT = imread("src/main/resources/input/catty.pgm");
        var NEG_CAT = sub(255, POS_CAT);
        imwrite("src/main/resources/output/catty.pgm", NEG_CAT);
    }
}
```
All Example Documented to the following path:
[src/main/java/com/swarna/imageprocessing/algo/...](src/main/java/com/swarna/imageprocessing/algo)


## Contribution

- Contributed by Swarnava Chakraborty with ❤️


## Contact

- Gmail: swarnava.code
- Medium: @swarnava-code
- LinkedIn: swarnavac
