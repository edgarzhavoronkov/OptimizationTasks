package ru.ifmo.nsf.mathdep.optimization.lab2;

import javafx.util.Pair;
import ru.ifmo.nsf.mathdep.optimization.lab2.utils.Point2D;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Created by edgar on 09.03.15.
 */
public class GradientDescentMinimizer implements Minimizer {
    private final double EPS = 1e-5;

    @Override
    public Point2D minimize(BiFunction<Double, Double, Double> f,
                            Point2D startPoint,
                            Predicate<Double> domX,
                            Predicate<Double> domY,
                            double precision,
                            double stepSize) {
        while (true) {
            Point2D nextPoint = Point2D.sub(startPoint, Point2D.mul(getGradient(f, startPoint), stepSize));
            if (getNorm(f.apply(nextPoint.getX(), nextPoint.getY()) - f.apply(startPoint.getX(), startPoint.getY())) < precision || domX.test(nextPoint.getX()) || domY.test(nextPoint.getY()))  return nextPoint;
            startPoint = new Point2D(nextPoint.getX(), nextPoint.getY());
        }
    }

    @Override
    public Point2D minimize(BiFunction<Double, Double, Double> f,
                            Point2D startPoint,
                            Predicate<Double> domX,
                            Predicate<Double> domY,
                            double precision) {
        return null;
    }

    private Point2D getGradient(BiFunction<Double, Double, Double> f, Point2D point) {
        double x = (f.apply(point.getX() + EPS, point.getY()) - f.apply(point.getX(), point.getY())) / EPS;
        double y = (f.apply(point.getX(), point.getY() + EPS) - f.apply(point.getX(), point.getY())) / EPS;
        return new Point2D(x, y);
    }

    private double getNorm(double x) {
        return x;
    }

    private double getNorm(Point2D point) {
        return Math.sqrt( point.getX() * point.getX() ) + ( point.getY() * point.getY() );
    }
}
