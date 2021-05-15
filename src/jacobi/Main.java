package jacobi;

public class Main {
    public static void main(String[] args)
    {
        Matrix A = new Matrix(4);
        Matrix B = new Matrix(4, 1, 1);
        System.out.println(A);
        System.out.println(A.multiply(B));
    }
}
