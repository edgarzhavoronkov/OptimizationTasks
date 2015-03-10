package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.util.function.DoubleFunction;


public interface Minimizer {
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision);
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, int iterations);
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L, int startTests);
}
