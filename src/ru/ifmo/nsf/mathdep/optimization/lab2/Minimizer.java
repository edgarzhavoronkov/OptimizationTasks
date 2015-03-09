package ru.ifmo.nsf.mathdep.optimization.lab2;

import javafx.util.Pair;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Created by edgar on 09.03.15.
 */
public interface Minimizer {
    public Pair<Double, Double> minimize(BiFunction<Double, Double, Double> f, Pair<Double, Double> startPoint, Predicate<Double> domX, Predicate<Double> domY, double precision);
}
