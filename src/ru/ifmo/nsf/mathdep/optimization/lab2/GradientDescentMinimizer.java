package ru.ifmo.nsf.mathdep.optimization.lab2;

import ru.ifmo.nsf.mathdep.optimization.lab2.utils.Point2D;

import java.util.function.BiFunction;
import java.util.function.Predicate;


public class GradientDescentMinimizer implements Minimizer {
    private final double EPS = 1e-5;

    @Override
    public Point2D minimize(BiFunction<Double, Double, Double> f,
                            Point2D startPoint,
                            Predicate<Double> domX,
                            Predicate<Double> domY,
                            double precision,
                            double stepSize) {
        Point2D nextPoint = new Point2D();
        for (;;) {
            Point2D grad = getGradient(f, startPoint);
            nextPoint = Point2D.sub(startPoint, Point2D.mul(grad, stepSize));

            Boolean stopCriteria1 = getNorm(Point2D.sub(nextPoint, startPoint)) < precision;
            Boolean stopCriteria2 = domX.test(nextPoint.getX());
            Boolean stopCriteria3 = domY.test(nextPoint.getY());

            if (stopCriteria1 || stopCriteria2 || stopCriteria3 )  return nextPoint;

            startPoint = new Point2D(nextPoint.getX(), nextPoint.getY());
        }
        //return nextPoint;
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
        return x * x;
    }

    private double getNorm(Point2D point) {
        return Math.sqrt( point.getX() * point.getX() ) + ( point.getY() * point.getY() );
    }
}
