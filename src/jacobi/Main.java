package jacobi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        przykład z zadania

        Scanner input = new Scanner(System.in);

        double epsilon = -1;
        while (epsilon<0) {
            System.out.print("Podaj epsilon: ");
            epsilon = input.nextDouble();
        }

//        double[] lx_0 = {1, 1, 1, 1};
        double[] lx_0 = new double[4];
        System.out.println("Podaj wektor startowy metody Jacobiego:");
        for (int i = 0; i < 4; i++) {
            System.out.print("Podaj x_" + (i+1) + ": ");
            lx_0[i] = input.nextDouble();
        }
        input.close();
        Matrix x_0 = new Matrix(lx_0, 4, 1);


        double[] lA = {
                20, -1, -2, -3,
                5, 20, -3, -4,
                4, 3, 20, -5,
                3, 2, 1, 20};
        Matrix A = new Matrix(lA, 4, 4);

        double[] lf = {85, 25, 93, 61};
        Matrix f = new Matrix(lf, 4, 1);


        JacobiMethod method = new JacobiMethod(A, f, x_0);
        method.iterate(epsilon);
        System.out.println(method);

//        przykład z dokumentacji
//        double[] lA = {
//                2, 1, -1,
//                6, 2, -2,
//                -4, -3, 4};
//
//        double[] lf = {6, 16, -15};
//        double[] lx_0 = {1, 1, 1};
//        JacobiMethod method = new JacobiMethod(
//                new Matrix(lA, 3, 3),
//                new Matrix(lf, 3, 1),
//                new Matrix(lx_0, 3,1)
//        );
//        System.out.println(method);
//        method.iterateOnce();
//        System.out.println(method.calculateError());
//        System.out.println(method);
    }
}
