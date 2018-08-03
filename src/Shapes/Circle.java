package Shapes;

import Points.Point;

public class Circle {
    Point center;
    int scale;
    double radius;
    double thickness;
    CircleData circleData;

    Circle (Point center, int scale, double radius, double thickness) {
        this.center = center;
        this.scale = scale;
        this.radius = radius;
        this.thickness = thickness;
    }

    public interface CircleData {
        double sampleSize(Point center, double radius, double thickness);
        double error(Point center, double radius, double thickness);
        double[] gradient(Point center, double radius, double thickness);
    }
}
