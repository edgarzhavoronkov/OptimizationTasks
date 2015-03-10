package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.util.ArrayList;
import java.util.function.DoubleFunction;


public class BruteForceMinimizer implements SingleArgumentFunctionMinimizer {
    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        return 0;
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, int iterations) {
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i <= iterations; i++) {
            values.add(f.apply(lowerBound + i * (higherBound - lowerBound) / (iterations + 1)));
        }
        return values.stream().mapToDouble(i -> i).min().getAsDouble();
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L, int startTests) {
        return 0;
    }
}
