package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.util.function.DoubleFunction;


public class DichotomyMinimizer implements SingleArgumentFunctionMinimizer {
    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        double mid = 0;
        while (!(Math.abs(higherBound - lowerBound) < precision)) {
            mid = (higherBound + lowerBound) / 2;
            double fLeft = f.apply(mid - precision);
            double fRight = f.apply(mid + precision);
            if (fLeft < fRight) {
                higherBound = mid;
            } else {
                lowerBound = mid;
            }
        }
        return f.apply(mid);
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
