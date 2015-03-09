package ru.ifmo.nsf.mathdep.optimization;

import com.sun.javafx.collections.MappingChange;
import javafx.util.Pair;
import ru.ifmo.nsf.mathdep.optimization.lab1.*;
import ru.ifmo.nsf.mathdep.optimization.lab1.Minimizer;
import ru.ifmo.nsf.mathdep.optimization.lab2.*;
import ru.ifmo.nsf.mathdep.optimization.lab2.utils.Point2D;

import java.util.Map;


/**
 * Created by edgar on 09.03.15.
 */
public class main {
    public static void main(String[] args) {
        Minimizer minimizer = new DichotomyMinimizer();
        Minimizer anotherMinimizer = new GoldenRatioMinimizer();
        Minimizer yetAnotherMinimizer = new FibonacciMinimizer();
        Minimizer yetYetAnotherMinimizer = new PoligonalChainMinimizer();
        Minimizer yetYetYetAnotherMinimizer = new BruteForceMinimizer();

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

        ru.ifmo.nsf.mathdep.optimization.lab2.Minimizer descentMinimizer = new GradientDescentMinimizer();
        Point2D res = descentMinimizer.minimize(
                (Double x, Double y) -> x * x * x * x + y * y * y * y - 5 * (x * y - x * x * y * y),
                new Point2D(0.2, 0.8),
                (Double x) -> x >= 0 && x <= 1,
                (Double y) -> y >= 0 && y <= 1,
                1e-5,
                1e-3);
        System.out.println(res.toString());
    }
}
