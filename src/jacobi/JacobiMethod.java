package jacobi;

public class JacobiMethod {

    static int maxSteps = 99;
    Matrix A;
    Matrix f;
    Matrix x_0;
    Matrix B;
    Matrix bj;


    JacobiMethod(Matrix A, Matrix f, Matrix x_0) {
        try {
            if (A.numRows() != A.numCols())
                throw new Exception("The A matrix must be square.");
            if (f.numRows() != A.numRows() || f.numCols() != 1)
                throw new Exception("Invalid f vector.");
            if (x_0.numRows() != A.numRows() || x_0.numCols() != 1)
                throw new Exception("Invalid x_0 vector.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.A = A;
        this.f = f;
        this.x_0 = x_0;

        B = getInvertedDiagonal().multiply(-1);
        B = B.multiply(getNonDiagonal());
        bj = getInvertedDiagonal().multiply(f);
    }

    Matrix getNonDiagonal() {
        Matrix nD = new Matrix(A);
        for (int i = 0; i < nD.numRows(); i++) {
            nD.a[i][i] = 0;
        }
        return nD;
    }
    Matrix getInvertedDiagonal() {
        Matrix D = new Matrix(A.numRows(), A.numCols(), 0);
        for (int i = 0; i < D.numRows(); i++) {
            D.a[i][i] = 1/A.a[i][i];
        }
        return D;
    }

//    Matrix getLeft() {
//        Matrix L = new Matrix(A);
//
//        for (int i = 0; i < L.numRows(); i++) {
//            for (int j = i; j < L.numCols(); j++) {
//                L.a[i][j] = 0;
//            }
//        }
//        return L;
//    }
//    Matrix getRight() {
//        Matrix R = new Matrix(A);
//        for (int i = 0; i < R.numRows(); i++) {
//            for (int j = 0; j <= i; j++) {
//                R.a[i][j] = 0;
//            }
//        }
//        return R;
//    }

    Matrix iterateOnce(){
        x_0 = B.multiply(x_0).add(bj);
        return x_0;
    }

    double calculateError() {
        double error = 0;
        double summand;
        for (int i = 0; i < A.numRows(); i++) {
            summand = -1*f.a[i][0];
            for (int j = 0; j < A.numCols(); j++) {
                summand += A.a[i][j] * x_0.a[j][0];
            }
            error += Math.abs(summand);
        }
        return error;
    }

    Matrix iterate(double epsilon) {
        for (int steps = 0;  steps < maxSteps; steps++) {
            if (calculateError() <= epsilon) {
                System.out.println("Znaleziono przybliżenie w " + steps + " krokach.");
                System.out.println("Błąd wyniósł " + calculateError() + ".");
                return x_0;
            }
            iterateOnce();
        }
        System.out.println("Przekroczono maksymalną liczbę kroków. (" + maxSteps + ")");
        System.out.println("Błąd wyniósł " + calculateError() + ".");
//        System.out.println(x_0);
        return x_0;
    }


    @Override
    public String toString() {
        return "Macierz B_j:\n" + B.toString()
                + "\n aktualne przybliżenie:\n"
                + x_0.toString();
    }

}
