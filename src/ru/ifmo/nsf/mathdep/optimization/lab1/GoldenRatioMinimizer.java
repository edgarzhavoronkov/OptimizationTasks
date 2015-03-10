package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.util.function.DoubleFunction;


public class GoldenRatioMinimizer implements SingleArgumentFunctionMinimizer {
    private final double GOLDEN_RATIO = (Math.sqrt(5) + 1) / 2;

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        while (!(Math.abs(higherBound - lowerBound) < precision)) {
            double left = higherBound -((higherBound - lowerBound) / GOLDEN_RATIO);
            double right = lowerBound + ((higherBound - lowerBound) / GOLDEN_RATIO);

            double fLeft = f.apply(left);
            double fRight = f.apply(right);

            if (fLeft >= fRight) {
                lowerBound = left;
            } else {
                higherBound = right;
            }
        }
        return f.apply((lowerBound + higherBound) / 2);
    }

    public double argmin(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        while (!(Math.abs(higherBound - lowerBound) < precision)) {
            double left = higherBound -((higherBound - lowerBound) / GOLDEN_RATIO);
            double right = lowerBound + ((higherBound - lowerBound) / GOLDEN_RATIO);

            double fLeft = f.apply(left);
            double fRight = f.apply(right);

            if (fLeft >= fRight) {
                lowerBound = left;
            } else {
                higherBound = right;
            }
        }
        return (lowerBound + higherBound) / 2;
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
