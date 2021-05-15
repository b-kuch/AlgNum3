package jacobi;

public class JacobiMethod {

    // method input data
    Matrix A;
    Matrix f;
    Matrix x_0;

    JacobiMethod(Matrix A, Matrix f, Matrix x_0) {
        try {
            if (f.numRows() != A.numRows() || f.numCols() != 1)
                throw new Exception("Invalid f vector.");
            if (x_0.numRows() != 1 || x_0.numCols() != A.numCols())
                throw new Exception("Invalid x_0 vector.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.A = A;
        this.f = f;
        this.x_0 = x_0;
    }


}
