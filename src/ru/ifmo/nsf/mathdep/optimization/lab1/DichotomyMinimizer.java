package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.DoubleFunction;


public class DichotomyMinimizer implements SingleArgumentFunctionMinimizer {
    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        try {
            double mid = 0;
            double finalLength = higherBound - lowerBound;
            PrintWriter writer = new PrintWriter("DichotomyMinimizerOut.txt");
            int counter = 0;
            while (!(Math.abs(higherBound - lowerBound) < precision)) {
                mid = (higherBound + lowerBound) / 2;

                //debug info, todo: remove
                double intervalLength = higherBound - lowerBound;


                double fLeft = f.apply(mid - precision);
                counter++;
                double fRight = f.apply(mid + precision);
                counter++;
                if (fLeft < fRight) {
                    higherBound = mid;
                } else {
                    lowerBound = mid;
                }

                finalLength = higherBound - lowerBound;
                writer.format("%.5f %.5f %.5f %.5f\n", lowerBound, higherBound, finalLength, (finalLength / intervalLength));
            }
            writer.format("I calculated function for %d times", counter);
            writer.close();
            return f.apply(mid);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, int iterations) {
        return 0;
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L, int startTests) {
        return 0;
    }
}
