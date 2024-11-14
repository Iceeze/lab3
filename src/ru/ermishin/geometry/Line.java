package ru.ermishin.geometry;

import ru.ermishin.main.Measurable;
import ru.ermishin.main.HasPolyLine;

public class Line implements Measurable, HasPolyLine {
    private Point p1, p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public double getLength() {
        return p1.distanceToPoint(p2);
    }

    @Override
    public String toString() {
        return "Линия от точки " + p1 + " до точки " + p2;
    }

    public PolyLine getPolyLine() {
        Point[] points = new Point[2];
        points[0] = p1;
        points[1] = p2;
        return new PolyLine(points);
    }
}