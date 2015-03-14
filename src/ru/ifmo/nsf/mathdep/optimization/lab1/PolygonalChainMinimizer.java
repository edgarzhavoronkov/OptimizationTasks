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
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L) {
        try {
            GoldenRatioMinimizer minimizer = new GoldenRatioMinimizer();
            PrintWriter writer = new PrintWriter("PolygonalChainMinimizerOut.txt");
            int counter = 0;

            //zero iteration

            double x0 = (lowerBound + higherBound) / 2;

            double f0 = f.apply(x0);

            DoubleFunction<Double> g0 = (x) -> f0 - L * Math.abs(x - x0);

            double x1 = minimizer.argmin(g0, lowerBound, higherBound, precision);

            double xPrev = x1;

            DoubleFunction<Double> pPrev = g0;
            writer.format("Polygonal chain:\n");
            writer.format("%.5f -> %.5f\n", x0, g0.apply(x0));

            while (true) {
                final double finalXPrev = xPrev;
                DoubleFunction<Double> gi = (x) -> f.apply(finalXPrev) - L * Math.abs(x - finalXPrev);
                final DoubleFunction<Double> finalPPrev = pPrev;
                DoubleFunction<Double> pi = (x) -> Math.max(gi.apply(x), finalPPrev.apply(x));
                double xNext = minimizer.argmin(pi, lowerBound, higherBound, precision);

                writer.format("%.5f -> %.5f\n", xPrev, pPrev.apply(xPrev));

                if (Math.abs(xNext - xPrev) < precision) {
                    writer.close();
                    return f.apply(xNext);
                }
                xPrev = xNext;
                pPrev = pi;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
