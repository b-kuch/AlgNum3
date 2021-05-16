package jacobi;

import java.util.Arrays;

public class Matrix {
    double[][] a;

    Matrix(int numRows, int numCols, double value) {
        try {
            if (numCols <= 0 || numRows <= 0) {
                throw new Exception("Invalid input in matrix constructor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.a = new double[numRows][numCols];
        for (int i=0; i<numRows; i++) {
            Arrays.fill(a[i], value);
        }
    }

    Matrix(double[] a, int numRows, int numCols) {
        try {
            if (a.length != numCols*numRows) {
                throw new Exception("Number of supplied values is not correct for a "
                        + numRows + "x" + numCols + " matrix.");
            }
            if (numCols <= 0 || numRows <= 0) {
                throw new Exception("Invalid input in matrix constructor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.a = new double[numRows][numCols];
        for (int i=0; i<numRows; i++) {
            System.arraycopy(a, numCols*i, this.a[i], 0, numCols);
        }
    }

    /** Identity matrix constructor*/
    Matrix(int size) {
        this(size, size, 0);

        for (int i = 0; i < size; i++) {
            this.a[i][i] = 1;
        }
    }

    public Matrix(Matrix matrix) {
        int numRows = matrix.numRows();
        int numCols = matrix.numCols();
        this.a = new double[numRows][numCols];
        for (int i=0; i<numRows; i++) {
            System.arraycopy(matrix.a[i], 0, this.a[i], 0, numCols);
        }
    }

    int numRows() {
        if (a!=null)
            return a.length;
        else
            return 0;
    }

    int numCols() {
        if (a!=null && a[0]!=null)
            return a[0].length;
        else
            return 0;
    }

    Matrix add(Matrix other) {
        try {
            if (this.numRows() != other.numRows() || this.numCols() != other.numCols()) {
                throw new Exception("Cannot add matrices of different sizes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Matrix sum = new Matrix(this);
        for (int i = 0; i < sum.numRows(); i++) {
            for (int j = 0; j < sum.numCols(); j++) {
                sum.a[i][j] += other.a[i][j];
            }
        }
        return sum;
    }

    /** Matrix multiplication */
    Matrix multiply(Matrix other) {
        try {
            if (this.numCols() != other.numRows() ) {
                throw new Exception("Cannot multiply matrices of given sizes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Matrix product = new Matrix(this.numRows(), other.numCols(), 0);

        for (int i = 0; i < product.numRows(); i++) {
            for (int j = 0; j < product.numCols(); j++) {

                for (int k = 0; k < this.numCols(); k++) {
                    product.a[i][j] += this.a[i][k] * other.a[k][j];
                }
            }
        }
        return product;
    }

    /** Scalar multiplication */
    Matrix multiply(double scalar) {
        Matrix product = new Matrix(this);
        for (int i = 0; i < product.numRows(); i++) {
            for (int j = 0; j < product.numCols(); j++) {
                product.a[i][j] *= scalar;
            }
        }
        return product;
    }

    double sumValues() {
        double sum = 0;
        for (int i = 0; i < numRows(); i++) {
            for (int j = 0; j < numCols(); j++) {
                sum += this.a[i][j];
            }
        }
        return sum;
    }

    static String format(double number) {
        return String.format("%+.2f", number);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("┌ ");
        for (int i = 0; i < numCols(); i++) {
            builder.append(format(a[0][i]));
            builder.append("\t");
        }
        builder.append("┐\n");

        for (int i = 1; i < numRows() - 1; i++) {
            builder.append("│ ");
            for (int j = 0; j < numCols(); j++) {
                builder.append(format(a[i][j]));
                builder.append("\t");
            }
            builder.append("│\n");
        }

        if (numRows() > 1) {
            builder.append("└ ");
            for (int i = 0; i < numCols(); i++) {
                builder.append(format(a[numRows()-1][i]));
                builder.append("\t");
            }
            builder.append("┘\n");
        }
        return builder.toString();
    }


}
