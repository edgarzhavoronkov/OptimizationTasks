package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.util.*;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;


public class PoligonalChainMinimizer implements Minimizer {
    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        return 0;
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, int iterations) {
        return 0;
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L, int startTests) {
        Map<Double, Double> observations = new HashMap<>();
        while (true) {
            double step = (higherBound - lowerBound) / startTests;
            double start = lowerBound;
            for (int i = 0; i < startTests; i++) {
                observations.put(start, f.apply(start));
                start += step;
            }
            double fk = observations.values().stream().mapToDouble(i -> i).min().getAsDouble();

            DoubleFunction<Double> minorant =
                    (x) -> {
                        observations.forEach((k, v) -> f.apply(v - L * getNorm(x - v)));
                        return observations.values().stream().mapToDouble(i -> i).min().getAsDouble();
                    };
            ArrayList<Double> pi = observations.keySet().stream().filter((i) -> f.apply(i) <= fk).collect(Collectors.toCollection(ArrayList::new));
            Map<Double, Double> fMinus = new HashMap<>();
            for (Double x : pi) {
                fMinus.put(x, minorant.apply(x));
            }

            double xNext = Collections.max(fMinus.entrySet(), new Comparator<Map.Entry<Double, Double>>() {
                @Override
                public int compare(Map.Entry<Double, Double> entry1, Map.Entry<Double, Double> entry2) {
                    return Double.compare(entry1.getValue(), entry2.getValue());
                }
            }).getKey();

            if (fk - minorant.apply(xNext) <= precision) return f.apply(xNext);

            startTests++;
        }
    }

    private double getNorm(double x) {
        return x;
    }


}
