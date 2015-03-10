package ru.ifmo.nsf.mathdep.optimization.lab2;

import ru.ifmo.nsf.mathdep.optimization.lab2.utils.Point2D;

import java.util.function.BiFunction;
import java.util.function.Predicate;


public interface DoubleArgumentFunctionMinimizer {
    public Point2D minimize(
            BiFunction<Double, Double, Double> f,
            Point2D startPoint,
            Predicate<Double> domX,
            Predicate<Double> domY,
            double precision,
            double stepSize);

    public Point2D minimize(
            BiFunction<Double, Double, Double> f,
            Point2D startPoint,
            Predicate<Double> domX,
            Predicate<Double> domY,
            double precision);
}
