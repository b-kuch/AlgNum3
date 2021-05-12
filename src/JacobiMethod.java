import java.util.Vector;

public class JacobiMethod {

    // method input data
    Matrix A;
    double[] f;
    double[] x_0;

    JacobiMethod(Matrix A, double[] f, double[] x_0) {
        try {
            this.A = A;
            this.f = f;
            this.x_0 = x_0;
            if (f.length != 4)
                throw new Exception("The f array does not have required four values");
            if (x_0.length != 4)
                throw new Exception("Starting vector does not have required four values.");
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }


}
