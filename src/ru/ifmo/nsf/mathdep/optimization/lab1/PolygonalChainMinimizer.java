package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;


public class PolygonalChainMinimizer implements SingleArgumentFunctionMinimizer {
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
        try {

            PrintWriter writer = new PrintWriter("PoligonalChainMinimizerOut.txt");
            Map<Double, Double> fMinus = new HashMap<>();
            ArrayList<Double> pi = new ArrayList<>();
            int counter = 0;
            while (true) {
                double step = (higherBound - lowerBound) / startTests;
                double start = lowerBound;
                Map<Double, Double> observations = new HashMap<>();
                for (int i = 0; i < startTests; i++) {
                    observations.put(start, f.apply(start));
                    start += step;
                    counter++;
                }
                double fk = observations.values().stream().mapToDouble(i -> i).min().getAsDouble();

                DoubleFunction<Double> minorant =
                        (x) -> {
                            ArrayList<Double> vals = new ArrayList<>();
                            observations.forEach((Double k, Double v) -> vals.add(v - L * Math.abs(x - k)));
                            return vals.stream().mapToDouble(i -> i).max().getAsDouble();
                        };

                pi.addAll(observations.keySet().stream().filter((i) -> f.apply(i) <= fk).collect(Collectors.toCollection(ArrayList::new)));

                for (Double x : pi) {
                    fMinus.put(x, minorant.apply(x));
                }

                double xNext = Collections.min(fMinus.entrySet(), new Comparator<Map.Entry<Double, Double>>() {
                    @Override
                    public int compare(Map.Entry<Double, Double> entry1, Map.Entry<Double, Double> entry2) {
                        return Double.compare(entry1.getValue(), entry2.getValue());
                    }
                }).getKey();

                if (Math.abs(fk - minorant.apply(xNext)) <= precision) {
                    writer.write("Function: \n");
                    for (Map.Entry<Double, Double> entry : observations.entrySet()) {
                        writer.format("%.5f %.5f\n", entry.getKey(), entry.getValue());
                    }
                    writer.write("Polygonal chain: \n");
                    for (Map.Entry<Double, Double> entry: fMinus.entrySet()) {
                        writer.format("%.5f %.5f\n", entry.getKey(), entry.getValue());
                    }
                    writer.format("I calculated function for %d times", counter);
                    writer.close();
                    return f.apply(xNext);
                }
                startTests++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
