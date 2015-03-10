package ru.ifmo.nsf.mathdep.optimization.lab1;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.DoubleFunction;


public class FibonacciMinimizer implements Minimizer {


    private Long fib(int n) {
        if (n > 0 && n < 2) {
            return (long) n;
        } else {
            long a = 0;
            long b = 1;
            for (int i = 2; i <= n; i++) {
                long temp = b;
                b = a + b;
                a = temp;
            }
            return b;
        }
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        throw new NotImplementedException();
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, int iterations) {
        double left = lowerBound + (higherBound - lowerBound) * (fib(iterations - 2) / fib(iterations));
        double right = lowerBound + (higherBound - lowerBound) * (fib(iterations - 1) / fib(iterations));

        double fLeft = f.apply(left);
        double fRight = f.apply(right);

        while (iterations != 1) {
            if (fLeft > fRight) {
                lowerBound = left;
                left = right;
                right = higherBound - (left - lowerBound);
                fLeft = fRight;
                fRight = f.apply(right);
            } else {
                higherBound = right;
                right = left;
                left = lowerBound + (higherBound - right);
                fRight = fLeft;
                fLeft = f.apply(left);
            }
            --iterations;
        }
        return f.apply(left);
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L, int startTests) {
        return 0;
    }
}
