package jacobi;

public class Matrix {
    // macierz 4x4
    double[][] a = new double[4][4];

    Matrix(double[][] a) {
        this.a = a;
    }

    Matrix(double[] a) {
        for (int i=0; i<4; i++) {
            System.arraycopy(a, 4*i, this.a[i], 0, 4);
        }
    }

}
