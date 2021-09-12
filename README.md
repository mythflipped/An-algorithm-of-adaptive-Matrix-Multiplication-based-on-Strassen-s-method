# An-algorithm-of-adaptive-Matrix-Multiplication-based-on-Strassen-s-method
# How it works

Two classes: StrassenAndTradition and Matrix.

## In StrassenAndTradition: four important methods:

```java
public static int[][]strassen_mutliply (Matrix matrixA, Matrix matrixB)
```

Use Strassen’s algorithm to multiply 2 matrices a and b, return their product.

```java
public static int[][]square_matrix_mutliplyv(int[][] matrix1, int[][] matrix2)
```

Use the standard approach to multiply 2 matrices a and b, return their product.

```java
public static void  calibrate_crossover_point()
```

Estimates a value for the crossover point for matrix size to decide whether to use Strassen’s approach or the standard approach and save this value to a configuration file.

```java
public static int[][] adaptive_multiply(int[][]matrixA, int[][]matrixB)
```

For matrices that are less than the (previously stored or default) crossover point use standard matrix multiplication, for matrices that are greater than the crossover point use Strassen’s method.

## In matrix: three methods 

```java
public Matrix(int[][] matix)
```

Matrix will represent a matrix in “row-major” order (i.e. for an 𝑛 × 𝑛-matrix the first row will be stored in an array at index 0 to index n-1 the next row at n to 2n-1 and so on). And store the values.

```java
public int getStore(int row,int column)
```

get the value of matrix in certain position.

```java
public int setStore(int row,int column)
```

set the value of matrix in certain position

additional: the main method is written in class StrassenAndTradition. You can import a number. And the program will create two random matrix A and B then show these two matrix and use the standard approach to multiply 2 matrices A and B, return their product. Then use the standard approach to multiply 2 matrices A and B, return their product. And show the runtime each other. 

# Efficiency

When the size of matrix is not too big, the program will calculate the answer very quickly. But when you use huge size n or use method `calibrate_crossover_point()` or `adaptive_multiply `,the runtime may be too long. The exact runtime depends on your computer performance.
