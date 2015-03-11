package ru.ifmo.nsf.mathdep.optimization;

import ru.ifmo.nsf.mathdep.optimization.lab1.*;
import ru.ifmo.nsf.mathdep.optimization.lab2.DoubleArgumentFunctionMinimizer;
import ru.ifmo.nsf.mathdep.optimization.lab2.GradientDescentMinimizer;
import ru.ifmo.nsf.mathdep.optimization.lab2.utils.Point2D;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;


public class main {
    public static void main(String[] args) {
        SingleArgumentFunctionMinimizer minimizer = new DichotomyMinimizer();
        SingleArgumentFunctionMinimizer anotherMinimizer = new GoldenRatioMinimizer();
        SingleArgumentFunctionMinimizer yetAnotherMinimizer = new FibonacciMinimizer();
        SingleArgumentFunctionMinimizer yetYetAnotherMinimizer = new PoligonalChainMinimizer();
        SingleArgumentFunctionMinimizer yetYetYetAnotherMinimizer = new BruteForceMinimizer();

        DoubleFunction<Double> f = (x) -> x * x + 3;
        BiFunction<Double, Double, Double> g = (x, y) -> (1 - x) * (1 - x) + 100 * (y - x * x) *(y - x * x);

        double min = minimizer.minimize(f, 0, 1, 1e-3);
        double anotherMin = anotherMinimizer.minimize(f, 0, 1, 1e-3);
        double yetAnotherMin = yetAnotherMinimizer.minimize(f, 0, 1, 20);
        double yetYetAnotherMin = yetYetAnotherMinimizer.minimize(f, 0, 1, 1e-3, 2, 10);
        double yetYetYetAnotherMin = yetYetYetAnotherMinimizer.minimize(f, 0, 1, 1000);

        System.out.println(min);
        System.out.println(anotherMin);
        System.out.println(yetAnotherMin);
        System.out.println(yetYetAnotherMin);
        System.out.println(yetYetYetAnotherMin);

        DoubleArgumentFunctionMinimizer descentMinimizer = new GradientDescentMinimizer();

        Point2D res = descentMinimizer.minimize(
                g,
                new Point2D(2, 2),
                (Double x) -> false,
                (Double y) -> false,
                1e-2,
                1e-4);

        Point2D anotherRes = descentMinimizer.minimize(
                g,
                new Point2D(2, 2),
                (Double x) -> false,
                (Double y) -> false,
                1e-2);

        System.out.printf("min g = %f at point %s using constant step length\n", g.apply(res.getX(), res.getY()), res.toString());
        System.out.printf("min g = %f at point %s using variadic step length\n", g.apply(res.getX(), res.getY()), anotherRes.toString());
    }
}
