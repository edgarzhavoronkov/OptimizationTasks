package ru.ifmo.nsf.mathdep.optimization;

import ru.ifmo.nsf.mathdep.optimization.lab1.*;


/**
 * Created by edgar on 09.03.15.
 */
public class main {
    public static void main(String[] args) {
        Minimizer minimizer = new DichotomyMinimizer();
        Minimizer anotherMinimizer = new GoldenRatioMinimizer();
        Minimizer yetAnotherMinimizer = new FibonacciMinimizer();
        //Minimizer yetYetAnotherMinimizer = new PoligonalChainMinimizer();
        Minimizer yetYetYetAnotherMinimizer = new BruteForceMinimizer();

        double min = minimizer.minimize((x) -> x * x + 3, 0, 1, 1e-3);
        double anotherMin = anotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 1e-3);
        double yetAnotherMin = yetAnotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 20);
        //double yetYetAnotherMin = yetYetAnotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 1e-3, 2, 10);
        double yetYetYetAnotherMin = yetYetYetAnotherMinimizer.minimize((x) -> x * x + 3, 0, 1, 1000);
        System.out.println(min);
        System.out.println(anotherMin);
        System.out.println(yetAnotherMin);
        System.out.println(yetYetYetAnotherMin);
    }
}
