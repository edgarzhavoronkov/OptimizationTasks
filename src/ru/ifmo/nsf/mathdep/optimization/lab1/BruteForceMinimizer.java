package ru.ifmo.nsf.mathdep.optimization.lab1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.DoubleFunction;


public class BruteForceMinimizer implements SingleArgumentFunctionMinimizer {
    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision) {
        return 0;
    }
    /* Метод перебора по сетке разбивает отрезок точками
     * и считает в каждой точке значение функции
     * и берет наименьшее из получившихся. Очевидно, что чем больше
     * точек мы возьмем, то с большей вероятностью мы найдем точный минимум.
     */
    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, int iterations) {
        ArrayList<Double> values = new ArrayList<>();
        try {
            PrintWriter writer = new PrintWriter("BruteforceMinimizerOut.txt");
            for (int i = 0; i <= iterations; i++) {
                values.add(f.apply(lowerBound + i * (higherBound - lowerBound) / (iterations + 1)));
            }
            writer.format("I calculated function for %d times", iterations);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return values.stream().mapToDouble(i -> i).min().getAsDouble();
    }

    @Override
    public double minimize(DoubleFunction<Double> f, double lowerBound, double higherBound, double precision, double L) {
        return 0;
    }
}
