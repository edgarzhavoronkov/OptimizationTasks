package ru.ifmo.nsf.mathdep.optimization;

import ru.ifmo.nsf.mathdep.optimization.lab1.*;
import ru.ifmo.nsf.mathdep.optimization.lab2.DoubleArgumentFunctionMinimizer;
import ru.ifmo.nsf.mathdep.optimization.lab2.GradientDescentMinimizer;
import ru.ifmo.nsf.mathdep.optimization.lab2.utils.Point2D;


public class main {
    public static void main(String[] args) {
        SingleArgumentFunctionMinimizer minimizer = new DichotomyMinimizer();
        SingleArgumentFunctionMinimizer anotherMinimizer = new GoldenRatioMinimizer();
        SingleArgumentFunctionMinimizer yetAnotherMinimizer = new FibonacciMinimizer();
        SingleArgumentFunctionMinimizer yetYetAnotherMinimizer = new PoligonalChainMinimizer();
        SingleArgumentFunctionMinimizer yetYetYetAnotherMinimizer = new BruteForceMinimizer();

        double min = minimizer.minimize((x) -> x * x + 3, 0, 1, 1e-3);
        double anotherMin = anotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 1e-3);
        double yetAnotherMin = yetAnotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 20);
        double yetYetAnotherMin = yetYetAnotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 1e-3, 2, 10);
        double yetYetYetAnotherMin = yetYetYetAnotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 1000);

        System.out.println(min);
        System.out.println(anotherMin);
        System.out.println(yetAnotherMin);
        System.out.println(yetYetAnotherMin);
        System.out.println(yetYetYetAnotherMin);

        DoubleArgumentFunctionMinimizer descentMinimizer = new GradientDescentMinimizer();
        Point2D res = descentMinimizer.minimize(
                (Double x, Double y) -> (1 - x) * (1 - x) + 100 * (y - x * x) *(y - x * x),
                new Point2D(0, 0),
                (Double x) -> false,
                (Double y) -> false,
                1e-5,
                1e-4);

        Point2D anotherRes = descentMinimizer.minimize(
                (Double x, Double y) -> (1 - x) * (1 - x) + 100 * (y - x * x) *(y - x * x),
                new Point2D(2, 2),
                (Double x) -> false,
                (Double y) -> false,
                1e-5);

        System.out.println(res.toString());
        System.out.println(anotherRes.toString());
    }
}
