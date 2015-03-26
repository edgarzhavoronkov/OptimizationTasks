package ru.ifmo.nsf.mathdep.optimization.lab1;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.DoubleFunction;


public class FibonacciMinimizer implements SingleArgumentFunctionMinimizer {

    /*
        Вспомогательная функция для подсчета энного числа Фибоначчи за линейное время
     */

    private Long fib(int n) {
        if (n >= 0 && n < 2) {
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
        try {
            PrintWriter writer = new PrintWriter("FibonacciMinimizerOut.txt");

            double left = lowerBound + (higherBound - lowerBound) * (fib(iterations - 2) * 1.0f / fib(iterations));
            double right = lowerBound + (higherBound - lowerBound) * (fib(iterations - 1) * 1.0f / fib(iterations));

            double finalLength = higherBound - lowerBound;

            double fLeft = f.apply(left);
            double fRight = f.apply(right);
            int counter = 2;
            int iters = iterations;
            while (iterations != 1) {
                double intervalLength = higherBound - lowerBound;
                if (fLeft > fRight) {
                    lowerBound = left;
                    left = right;
                    right = higherBound - (left - lowerBound);
                    fLeft = fRight;
                    fRight = f.apply(right);
                    counter++;
                } else {
                    higherBound = right;
                    right = left;
                    left = lowerBound + (higherBound - right);
                    fRight = fLeft;
                    fLeft = f.apply(left);
                    counter++;
                }
                finalLength = higherBound - lowerBound;
                writer.format("%.5f %.5f %.5f %.5f\n", lowerBound, higherBound, finalLength, (finalLength / intervalLength));
                --iterations;
            }
            writer.format("I did %d iterations and calculated function for %d times", iters, counter);
            writer.close();
            return f.apply(left);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L) {
        return 0;
    }
}
